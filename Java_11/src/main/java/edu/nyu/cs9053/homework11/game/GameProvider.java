package edu.nyu.cs9053.homework11.game;

import edu.nyu.cs9053.homework11.game.screen.InputMove;

/**
 * User: blangel
 */
public interface GameProvider {

    Difficulty getDifficulty();

    /**
     * @return a number between 0 and the {@linkplain Difficulty#getLevel()} value of the result of
     *         calling {@link #getDifficulty()}
     */
    int getRandomNumberOfNextFoes();

    /**
     * The next randomly selected move such that with 50% likelihood it is a vertical (up and down the y axis)
     * or horizontal move (back and forth across the x axis).
     * If it is a vertical move, it should be 50% likely to be {@linkplain InputMove#Up} and 50% likely to be {@linkplain InputMove#Down}
     * If it is a horizontal move, it should be 95% likely to be {@linkplain InputMove#Left} and 5% likely to be {@linkplain InputMove#Right}
     * @return the next move, randomly selected.
     */
    InputMove getRandomNextMove();
}
