package edu.nyu.cs9053.homework10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: blangel
 */
public class MiddleAgesFortification extends AbstractConcurrencyFactorProvider implements Fortification<Thread> {

    private final Thread[] threads;
    private final AtomicInteger currentRunning;
    private final BlockingQueue<Runnable> queue;

    public MiddleAgesFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        this.threads = new Thread[concurrencyFactor];
        this.currentRunning = new AtomicInteger(0);
        this.queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < concurrencyFactor; i++) {
            threads[i] = new Thread();
        }

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (MiddleAgesFortification.class) {
                    if (currentRunning.get() < getConcurrencyFactor()) {
                        if (!queue.isEmpty()) {
                            currentRunning.incrementAndGet();
                            for (Thread t : this.threads) {
                                if (!t.isAlive()) {
                                    t = new Thread(queue.poll());
                                    t.start();
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }).start();
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        synchronized (MiddleAgesFortification.class) {
            if (currentRunning.get() < getConcurrencyFactor()) {
                for (Thread t : this.threads) {
                    if (!t.isAlive()) {
                        currentRunning.incrementAndGet();
                        t = new Thread(() -> {
                            handler.soldiersReady();
                            currentRunning.decrementAndGet();
                        });
                        t.start();
                        return;
                    }
                }
            } else {
                queue.offer(() -> {
                    handler.soldiersReady();
                    currentRunning.decrementAndGet();
                });
            }
        }
    }

    @Override
    public void surrender() {
        for (Thread t : threads) {
            if (t != null) {
                t.interrupt();
            }
        }
    }
}