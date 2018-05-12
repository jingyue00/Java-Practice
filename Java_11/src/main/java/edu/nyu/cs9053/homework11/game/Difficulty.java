package edu.nyu.cs9053.homework11.game;

/**
 * User: blangel
 */
public enum Difficulty {

    Easy(1),

    Medium(2),

    Hard(3);

    private final int level;

    private Difficulty(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
