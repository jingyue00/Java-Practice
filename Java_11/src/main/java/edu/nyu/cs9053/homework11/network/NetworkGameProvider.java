package edu.nyu.cs9053.homework11.network;

/**
 * User: blangel
 */
public interface NetworkGameProvider {

    /**
     * @param difficulty the difficulty as a String value; see {@linkplain edu.nyu.cs9053.homework11.game.Difficulty#name()}
     * @return a random number of next foes (as a String) to generate based on the logic defined in {@linkplain edu.nyu.cs9053.homework11.game.GameProvider#getRandomNumberOfNextFoes()}
     * @see edu.nyu.cs9053.homework11.game.GameProvider
     */
    String getRandomNumberOfNextFoes(String difficulty);

    /**
     * The next randomly selected move such that with 50% likelihood it is a vertical (up and down the y axis)
     * or horizontal move (back and forth across the x axis).
     * If it is a vertical move, it should be 50% likely to be {@linkplain edu.nyu.cs9053.homework11.game.screen.InputMove#Up} and 50% likely to be {@linkplain edu.nyu.cs9053.homework11.game.screen.InputMove#Down}
     * If it is a horizontal move, it should be 95% likely to be {@linkplain edu.nyu.cs9053.homework11.game.screen.InputMove#Left} and 5% likely to be {@linkplain edu.nyu.cs9053.homework11.game.screen.InputMove#Right}
     *
     * @return the next move, randomly selected; one of "Up" "Down" "Left" or "Right".
     */
    String getRandomNextMove();

}
