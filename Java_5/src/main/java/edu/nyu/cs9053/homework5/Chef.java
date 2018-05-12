package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef { 

    private static final int DEFAULT_OVEN_TEMPERATURE = 300;
    private static final double RATE = 0.5d;

    public static void main(String[] args) {
        
        Oven oven = new Oven(DEFAULT_OVEN_TEMPERATURE);
        RoastedSweetPotato roastedSweetPotato = new RoastedSweetPotato(oven);
        PotRoast potRoast = new PotRoast(oven);
        Baguette baguette1 = new Baguette(oven, RATE);
        SousChef souschef = new SousChef(oven);
        Baguette baguette2 = new Baguette(oven, RATE);

        souschef.prepare(potRoast, new RecipeReadyCallback() {
            @Override
            public void recipeReadyToCook(Recipe recipe) {
                oven.cook(potRoast, new Timer() {
                    @Override
                    public void update(Time unit, int value, int ovenTemperature) {
                        beginCooking(potRoast, oven, unit, value, ovenTemperature);
                    }
                }, true);
            }
        });

        souschef.prepare(baguette1, new RecipeReadyCallback() {
            @Override
            public void recipeReadyToCook(Recipe recipe) {
                oven.cook(baguette1, new Timer() {
                    @Override
                    public void update(Time unit, int value, int ovenTemperature) {
                        beginCooking(baguette1, oven, unit, value, ovenTemperature);
                    }
                }, true);
            }
        });
        
        souschef.prepare(roastedSweetPotato, new RecipeReadyCallback() {
            @Override
            public void recipeReadyToCook(Recipe recipe) {
                oven.cook(roastedSweetPotato, new Timer() {
                    @Override
                    public void update(Time unit, int value, int ovenTemperature) {
                        beginCooking(roastedSweetPotato, oven, unit, value, ovenTemperature);
                    }
                }, true);
            }
        });
        
        souschef.prepare(baguette2, new RecipeReadyCallback() {
            @Override
            public void recipeReadyToCook(Recipe recipe) {
                oven.cook(baguette2, new Timer() {
                    @Override
                    public void update(Time unit, int value, int ovenTemperature) {
                        beginCooking(baguette2, oven, unit, value, ovenTemperature);
                    }
                }, true);
            }
        });
    }

    private static void beginCooking(Recipe recipe, Oven oven, Time unit, int value, int ovenTemperature) {
        if (recipe.isRecipeDone()) {
            oven.takeOut(recipe);
        } else {
            recipe.adjust(unit, value, ovenTemperature);
            checkReceipe(recipe, oven);
        }
    }

    private static void checkReceipe(Recipe recipe, Oven oven) {
        if (recipe.isRecipeDone()) {
            oven.takeOut(recipe);
        } else {
            continueCooking(recipe, oven);
        }
    }  

    private static void continueCooking(Recipe recipe, Oven oven) {
        oven.cook(recipe, new Timer() {
            @Override
            public void update(Time unit, int value, int ovenTemperature) {
                recipe.adjust(unit, value, ovenTemperature);
                checkReceipe(recipe, oven);
            }
        }, false);
    }
}