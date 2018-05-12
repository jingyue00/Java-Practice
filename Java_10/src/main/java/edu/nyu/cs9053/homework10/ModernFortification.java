package edu.nyu.cs9053.homework10;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: blangel
 */
public class ModernFortification extends AbstractConcurrencyFactorProvider implements Fortification<ExecutorService> {

    private final ExecutorService service;
    private final AtomicInteger currentRunning;
    private final BlockingQueue<Runnable> queue;

    public ModernFortification(int concurrencyFactor) {
        super(concurrencyFactor);
        this.currentRunning = new AtomicInteger(0);
        queue = new LinkedBlockingQueue<>();
        this.service = Executors.newFixedThreadPool(concurrencyFactor);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (currentRunning.get() < getConcurrencyFactor()) {
                    synchronized (ModernFortification.class) {
                        if (!queue.isEmpty()) {
                            currentRunning.incrementAndGet();
                            service.submit(queue.poll());
                        }
                    }
                } else {
                    try {
                        Thread.sleep(250L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void handleAttack(AttackHandler handler) {
        if (currentRunning.get() < getConcurrencyFactor()) {
            currentRunning.incrementAndGet();
            service.submit(() -> {
                handler.soldiersReady();
                currentRunning.decrementAndGet();
            });
        } else {
            queue.offer(() -> {
                handler.soldiersReady();
                currentRunning.decrementAndGet();
            });
        }
    }

    @Override
    public void surrender() {
        service.shutdownNow();
    }
}