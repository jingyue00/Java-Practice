package edu.nyu.cs9053.homework9;

public class Espresso implements CoffeeDrink {

    @Override
    public boolean isDecaf() {
        return false;
    }

    @Override
    public boolean containsMilk() {
        return false;
    }
}