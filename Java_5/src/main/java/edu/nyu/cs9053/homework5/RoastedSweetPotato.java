package edu.nyu.cs9053.homework5;

public class RoastedSweetPotato implements Recipe {

    private static final int ROASTEDSWEETPOTATO_VOLUME_CUBIC_INCHES = 6000;
    private static final int MINUTE_TO_SECOND = 60;
    private static final int TEMPERATURE_IN_MINUTES = 10;
    private Double remainingSecondsUntilDone;
    private int cookedTime;

    private static Oven oven;

    RoastedSweetPotato(Oven oven) {
        this.cookedTime = 0;
        this.remainingSecondsUntilDone = 1800.0d;
        this.initializeFromOven(oven);
    }

    @Override
    public void initializeFromOven(Oven oven) {
        this.oven = oven;
    }

    @Override 
    public int getVolumeCubicInches() {
        return ROASTEDSWEETPOTATO_VOLUME_CUBIC_INCHES;
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
            remainingSecondsUntilDone = ((double) ovenTemperature / TEMPERATURE_IN_MINUTES * MINUTE_TO_SECOND) - this.cookedTime;
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