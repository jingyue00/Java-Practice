package edu.nyu.cs9053.homework11.game.screen;

/**
 * User: blangel
 */
public enum Decoration {

    Normal(0),

    Bold(1),

    Blinking(5),

    Negative(7);

    private final int code;

    private Decoration(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
