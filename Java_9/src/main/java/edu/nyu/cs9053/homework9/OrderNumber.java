package edu.nyu.cs9053.homework9;

/**
 * User: blangel
 */
public class OrderNumber {

    private final int queueNumber;

    private final CoffeeDrink coffeeDrink;

    public OrderNumber(int queueNumber, CoffeeDrink coffeeDrink) {
        this.queueNumber = queueNumber;
        this.coffeeDrink = coffeeDrink;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public CoffeeDrink getCoffeeDrink() {
        return coffeeDrink;
    }
}
