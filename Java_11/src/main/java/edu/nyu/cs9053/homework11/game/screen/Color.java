package edu.nyu.cs9053.homework11.game.screen;

/**
 * User: blangel
 */
public enum Color {

    Black(0),

    Red(1),

    Green(2),

    Yellow(3),

    Blue(4),

    Magenta(5),

    Cyan(6),

    White(7);

    private final int code;

    private Color(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
