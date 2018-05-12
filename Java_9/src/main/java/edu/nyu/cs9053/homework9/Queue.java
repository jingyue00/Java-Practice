package edu.nyu.cs9053.homework9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * User: blangel
 */
public class Queue {

    private static final int DEFAULT_CAPACITY = 100;

    private final Collection<OrderNumber> orders;

    // the amount of orders which can be queued
    private final int capacity;

    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity) {
        this.orders = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public OrderNumber getOrderNumber() {
        Iterator<OrderNumber> iterator = orders.iterator();
        if (!iterator.hasNext()) {
            throw new UnsupportedOperationException();
        }
        OrderNumber orderNumber = iterator.next();
        iterator.remove();
        return orderNumber;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public boolean full() {
        return (orders.size() == capacity);
    }

    public OrderNumber addOrder(CoffeeDrink coffeeDrink) {
        if (full()) {
            throw new UnsupportedOperationException();
        }
        OrderNumber orderNumber = new OrderNumber(orders.size(), coffeeDrink); 
        orders.add(orderNumber);
        return orderNumber;
    }

}
