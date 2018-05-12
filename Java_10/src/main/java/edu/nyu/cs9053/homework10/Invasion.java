package edu.nyu.cs9053.homework10;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * User: blangel
 */
public class Invasion {

    private final Fortification<?> fortification;

    private final int concurrencyFactor;

    private final int attackSize;

    private final AtomicInteger running;

    private final Random random;

    private final ScheduledExecutorService executor;

    private final AtomicLong requestCounter;

    private final AtomicBoolean breached;

    public Invasion(Fortification<?> fortification, int concurrencyFactor) {
        this.fortification = fortification;
        this.concurrencyFactor = concurrencyFactor;
        this.running = new AtomicInteger(0);
        this.random = new Random();
        this.breached = new AtomicBoolean(false);
        long attackSizeLong = (concurrencyFactor * 250L);
        if (attackSizeLong > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        this.attackSize = (int) attackSizeLong;
        this.executor = Executors.newScheduledThreadPool(concurrencyFactor, new ThreadFactory() {
            private final AtomicInteger poolNumber = new AtomicInteger(1);
            private final ThreadGroup group;
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            private final String namePrefix;

            {
                SecurityManager s = System.getSecurityManager();
                group = (s != null) ? s.getThreadGroup() :
                        Thread.currentThread().getThreadGroup();
                namePrefix = "attacker-" +
                        poolNumber.getAndIncrement() +
                        "-thread-";
            }

            public Thread newThread(Runnable work) {
                Thread thread = new Thread(group, work, namePrefix + threadNumber.getAndIncrement(), 0);
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (thread.getPriority() != Thread.NORM_PRIORITY) {
                    thread.setPriority(Thread.NORM_PRIORITY);
                }
                return thread;
            }
        });
        this.requestCounter = new AtomicLong(0);
    }

    public void start() {
        System.out.printf("Starting invasion, hopefully you're fortified...%n");
        final long start = System.currentTimeMillis();
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override public void run() {
                if (breached.get()) {
                    System.out.printf("FAIL! You just got PWNED (Breach of your fortification, same invoking thread as handling thread)!%n");
                    fortification.surrender();
                    executor.shutdownNow();
                }
                int currentlyRunning = running.get();
                boolean tooMany = (currentlyRunning > concurrencyFactor);
                if (tooMany || ((System.currentTimeMillis() > (start + concurrencyFactor))
                        && (currentlyRunning < Math.max(1, concurrencyFactor / 2)))) {
                    System.out.printf("FAIL! You just got PWNED (%s)!%n",
                            (tooMany ? "Fortification overwhelmed" : "Fortification too few attacks being processed"));
                    fortification.surrender();
                    executor.shutdownNow();
                }
            }
        }, 250L, 250L, TimeUnit.MILLISECONDS);
        for (int i = 0; i < attackSize; i++) {
            executor.submit(new Runnable() {
                @Override public void run() {
                    process(this);
                }
            });
        }
    }

    private void process(final Runnable runnable) {
        final Thread invoker = Thread.currentThread();
        fortification.handleAttack(new AttackHandler() {
            @Override public void soldiersReady() {
                running.incrementAndGet();
                try {
                    System.out.printf("Fighting attack %d%n", requestCounter.incrementAndGet());
                    if (invoker == Thread.currentThread()) {
                        breached.set(true);
                    }
                    Thread.sleep(random.nextInt(5) * 1000L);
                    try {
                        executor.submit(runnable);
                    } catch (OutOfMemoryError oome) {
                        System.out.printf(
                                "FAIL! Not enough soldiers for this attack (OutOfMemoryError - try to increase the JVM heap size to thwart an invasion of this magnitude)!%n");
                        fortification.surrender();
                        executor.shutdownNow();
                    }
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                } finally {
                    running.decrementAndGet();
                }
            }
        });
    }

}
