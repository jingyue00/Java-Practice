package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

public class BaristaOrder implements Barista{

    private final Semaphore semaphore;

    public BaristaOrder(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public OrderNumber handle(Queue from) {
        if (from == null) {
            throw new IllegalArgumentException();
        }
        try {
            semaphore.acquire();
            try {
                if (from.isEmpty()) {
                    return null;
                }
                return from.getOrderNumber();
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }
}