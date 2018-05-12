package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 * Do not modify this class.
 */
public class SousChef {

    private final Oven oven;

    public SousChef(Oven oven) {
        this.oven = oven;
    }

    public void prepare(Recipe recipe, RecipeReadyCallback callback) {
        Thread thread = new Thread(new Runnable() {
            @Override public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (SousChef.this) {
                        if (oven.canFit(recipe)) {
                            callback.recipeReadyToCook(recipe);
                            return;
                        } else {
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException ie) {
                                throw new AssertionError(ie);
                            }
                        }
                    }
                }
            }
        });
        thread.start();
    }

}
