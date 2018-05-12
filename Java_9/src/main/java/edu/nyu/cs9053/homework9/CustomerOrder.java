package edu.nyu.cs9053.homework9;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CustomerOrder implements Customer {

    private final Random random;
    private final Semaphore semaphore;

    public CustomerOrder(Semaphore semaphore) {
        this.semaphore = semaphore;
        this.random = new Random();
    }

    @Override
    public OrderNumber order(Queue queue) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }
        try {
            semaphore.acquire();
            try {
                if (queue.full()) {
                    return null;
                }
                return queue.addOrder(getRandomCoffee());
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }

    private CoffeeDrink getRandomCoffee() {
        CoffeeDrink[] coffeeArray = new CoffeeDrink[]{new Cappuccino(), new DecafLatte(), new Espresso()};
        return coffeeArray[random.nextInt(3)];
    }
}