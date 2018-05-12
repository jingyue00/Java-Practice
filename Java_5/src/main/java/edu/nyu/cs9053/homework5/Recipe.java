package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 * Do not modify this interface.
 */
public interface Recipe {

    void initializeFromOven(Oven oven);

    /**
     * @return the food's volume in cubic inches
     */
    int getVolumeCubicInches();

    /**
     * @return the remaining seconds until the recipe is done. If 0, this recipe is complete and should
     *         be removed from the {@linkplain Oven}
     */
    Double getRemainingSecondsUntilDone();

    /**
     * Adjust the remaining seconds done based on the amount of update time and the current oven temperature
     * @param unit   of time which has update with the existing oven temperature
     * @param amount of time which has update with the existing oven temperature
     * @param ovenTemperature the potentially new current oven temperature
     */
    void adjust(Time unit, int amount, int ovenTemperature);

    /**
     * @return true if the recipe is done and should be removed from the oven
     */
    boolean isRecipeDone();

}
