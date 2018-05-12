package edu.nyu.cs9053.homework5;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: blangel
 * Do not modify this class.
 */
public class Oven {

    private static final int DEFAULT_OVEN_VOLUME_CUBIC_INCHES = 12000;

    private final Random random;

    private final int initialTemperature;

    private final int volumeCubicInches;

    private final AtomicReference<Integer> currentVolumeUsed;

    public Oven(int initialTemperature) {
        this(new Random(), initialTemperature, DEFAULT_OVEN_VOLUME_CUBIC_INCHES);
    }

    protected Oven(Random random, int initialTemperature, int volumeCubicInches) {
        this.random = random;
        this.initialTemperature = initialTemperature;
        this.volumeCubicInches = volumeCubicInches;
        this.currentVolumeUsed = new AtomicReference<>(0);
    }

    public int getSetTemperature() {
        return initialTemperature;
    }

    private Integer getTemperature() {
        return (initialTemperature + (random.nextInt(100) * (random.nextBoolean() ? -1 : 1)));
    }

    public synchronized boolean canFit(Recipe recipe) {
        return volumeCubicInches >= (recipe.getVolumeCubicInches() + currentVolumeUsed.get());
    }

    /**
     * @param recipe to cook
     * @param timer to use when checking on the recipe
     * @param putInOven true to indicate this is the first time the recipe is being put into the oven
     */
    public synchronized void cook(Recipe recipe, Timer timer, boolean putInOven) {
        if ((recipe == null) || (timer == null)) {
            throw new IllegalArgumentException("Recipe or Timer cannot be null");
        }
        if (putInOven) {
            if (!canFit(recipe)) {
                throw new IllegalArgumentException("Oven is already full!");
            }
            if (recipe.isRecipeDone()) {
                throw new IllegalArgumentException(String.format("Recipe %s is already done!", getRecipeLabel(recipe, false)));
            }
            System.out.printf("Starting to cook %s...%n", getRecipeLabel(recipe, false));
            currentVolumeUsed.set(currentVolumeUsed.get() + recipe.getVolumeCubicInches());
        } else {
            System.out.printf("  continuing to cook %s (remaining time %.0f sec)%n", getRecipeLabel(recipe, false), recipe.getRemainingSecondsUntilDone());
        }
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                Random random = new Random();
                int amount = random.nextInt(100);
                int unit = random.nextInt(2);
                Time time;
                switch (unit) {
                    case 0:
                        time = Time.Seconds;
                        break;
                    default:
                        time = Time.Minutes;
                        amount /= 10;
                }
                int sleep = random.nextInt(10);
                try {
                    Thread.sleep((sleep * 100L));
                    timer.update(time, amount, getTemperature());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ie);
                }
            }
        });
        thread.start();
    }

    private String getRecipeLabel(Recipe recipe, boolean unicode) {
        String name = recipe.getClass().getSimpleName().toLowerCase();
        String decoration = "";
        if (recipe.getClass() == Baguette.class) {
            name = "baguette";
            decoration = (unicode ? " \uD83C\uDF5E" : "");
        } else if (recipe.getClass() == PotRoast.class) {
            name = "pot roast";
            decoration = (unicode ? " \uD83C\uDF56" : "");
        } else if (recipe.getClass() == RoastedSweetPotato.class) {
            name = "roasted sweet potato";
            decoration = (unicode ? " \uD83C\uDF60" : "");
        }
        return String.format("%s%s", name, decoration);
    }

    public synchronized void takeOut(Recipe recipe) {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe must not be null");
        }
        System.out.printf("Taking out finished recipe %s ... mmmm!%n", getRecipeLabel(recipe, true));
        currentVolumeUsed.set(currentVolumeUsed.get() - recipe.getVolumeCubicInches());
    }

}
