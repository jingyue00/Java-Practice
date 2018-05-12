package edu.nyu.cs9053.homework5;

public class Baguette implements Recipe {

    private static final int BAGUETTE_VOLUME_CUBIC_INCHES = 2000;
    private static final int MINUTE_TO_SECOND = 60; 
    private static final double FINAL_TEMPERATURE = 0.01d;
    private Double remainingSecondsUntilDone;
    private int cookedTime;
    private double rate;

    private static Oven oven;

    Baguette (Oven oven, double rate) {
        this.cookedTime = 0;
        this.remainingSecondsUntilDone = 1200.0d;
        this.oven = oven;
        this.rate = rate;
    }

    @Override
    public void initializeFromOven(Oven oven) {
        this.oven = oven;
    }

    @Override 
    public int getVolumeCubicInches() {
        return BAGUETTE_VOLUME_CUBIC_INCHES;
    }

    @Override 
    public Double getRemainingSecondsUntilDone() {
        return remainingSecondsUntilDone;
    }

    @Override 
    public void adjust(Time unit, int amount, int ovenTemperature) {
        int cookedSeconds = 0;
        if (unit == Time.Seconds) {
            cookedSeconds = amount;
        } else {
            cookedSeconds = amount * MINUTE_TO_SECOND;
        }
        if (cookedSeconds != 0) {
            this.cookedTime += cookedSeconds;
            remainingSecondsUntilDone = (Math.log((double) ovenTemperature / FINAL_TEMPERATURE) / rate * MINUTE_TO_SECOND) - this.cookedTime;
        }
    }

    @Override 
    public boolean isRecipeDone() {
        if (remainingSecondsUntilDone <= 0) {
            return true;
        } else {
            return false;
        }
    }
}