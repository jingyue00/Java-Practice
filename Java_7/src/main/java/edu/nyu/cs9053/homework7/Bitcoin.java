package edu.nyu.cs9053.homework7;

public class Bitcoin implements Cryptocurrency {

    private final double amount;

    public Bitcoin(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || (getClass() != obj.getClass())) {
            return false;
        }
        Bitcoin that = (Bitcoin) obj;
        return this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(amount).hashCode();
    }
}
