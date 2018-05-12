package edu.nyu.cs9053.homework9;

import java.util.ArrayList;
import java.util.List;

/**
 * User: blangel
 */
public class Starbucks {

    public static void main(String[] args) {
        Queue queue = new Queue();
        Barista barista = Factory.createBarista();
        Customer customerOne = Factory.createCustomer();
        Customer customerTwo = Factory.createCustomer();
        Customer customerThree = Factory.createCustomer();
        @SuppressWarnings("serial")
        Starbucks starbucks = new Starbucks(queue, new ArrayList<Barista>(1) {{ add(barista); }},
                                                   new ArrayList<Customer>(3) {{ add(customerOne); add(customerTwo); add(customerThree); }});
        starbucks.start();
    }

    private final Queue queue;

    private final List<Barista> baristas;

    private final List<Customer> customers;

    public Starbucks(Queue queue, List<Barista> baristas, List<Customer> customers) {
        this.queue = queue;
        this.baristas = baristas;
        this.customers = customers;
    }

    private void start() {
        List<Thread> threads = new ArrayList<>(baristas.size() + customers.size());
        setupBaristas(threads);
        setupCustomers(threads);
        for (Thread thread : threads) {
            thread.start();
        }
        waitForThreads(threads);
        // failure, orderly shutdown and error message print
        orderlyShutdown(threads);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
        System.err.printf("Fail! Barista and/or Customer not implemented properly%n");
        System.exit(1);
    }

    private void setupBaristas(List<Thread> threads) {
        for (Barista barista : baristas) {
            Thread baristaThread = new Thread(new Runnable() {
                @Override public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        OrderNumber orderNumber = barista.handle(queue);
                        if (orderNumber != null) {
                            System.out.printf("Made order %d, %s%n", orderNumber.getQueueNumber(), orderNumber.getCoffeeDrink().getClass().getSimpleName());
                        }
                    }
                }
            });
            threads.add(baristaThread);
        }
    }

    private void setupCustomers(List<Thread> threads) {
        for (Customer customer : customers) {
            Thread consumerThread = new Thread(new Runnable() {
                @Override public void run() {
                    while (!Thread.currentThread().isInterrupted()) {
                        OrderNumber orderNumber = customer.order(queue);
                        if (orderNumber != null) {
                            System.out.printf("Ordered %s with order number %d%n", orderNumber.getCoffeeDrink().getClass().getSimpleName(), orderNumber.getQueueNumber());
                        }
                    }
                }
            });
            threads.add(consumerThread);
        }
    }

    private void waitForThreads(List<Thread> threads) {
        while (allAlive(threads)) {
            Thread.yield();
        }
    }

    private boolean allAlive(List<Thread> threads) {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private void orderlyShutdown(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

}
