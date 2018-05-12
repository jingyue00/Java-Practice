package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 * Do not modify this interface.
 */
public interface RecipeReadyCallback {

    /**
     * @param recipe is now ready and can be put into the oven
     */
    void recipeReadyToCook(Recipe recipe);

}
