package edu.nyu.cs9053.homework6;

/**
 * User: blangel
 *
 * Unnecessary to modify this file, so do not.
 *
 */
public class FancyOutput {

    private static final String CYAN_REPLACEMENT;

    private static final String YELLOW_REPLACEMENT;

    private static final String RED_REPLACEMENT;

    private static final String GREEN_REPLACEMENT;

    private static final String BOLD_REPLACEMENT;

    private static final String RESET_REPLACEMENT;

    private static final String DIAMOND_REPLACEMENT;

    private static final String BOMB_REPLACEMENT;

    private static final String EXPLOSION_REPLACEMENT;

    private static final String FIRE_REPLACEMENT;

    private static final String GHOST_REPLACEMENT;

    private static final String SNAKE_REPLACEMENT;

    private static final String CONSTRUCTION_REPLACEMENT;

    static {
        String terminal = System.getenv("TERM");
        if (terminal != null) {
            CYAN_REPLACEMENT = "\u001b[1;36m";
            YELLOW_REPLACEMENT = "\u001b[1;33m";
            RED_REPLACEMENT = "\u001b[1;31m";
            GREEN_REPLACEMENT = "\u001b[1;32m";
            BOLD_REPLACEMENT = "\u001b[1m";
            RESET_REPLACEMENT = "\u001b[0m";
            DIAMOND_REPLACEMENT = "\uD83D\uDC8E";
            BOMB_REPLACEMENT = "\uD83D\uDCA3";
            EXPLOSION_REPLACEMENT = "\uD83D\uDCA5";
            FIRE_REPLACEMENT = "\uD83D\uDD25";
            GHOST_REPLACEMENT = "\uD83D\uDC7B";
            SNAKE_REPLACEMENT = "\uD83D\uDC0D";
            CONSTRUCTION_REPLACEMENT = "\uD83D\uDEA7";
        } else {
            CYAN_REPLACEMENT = "";
            YELLOW_REPLACEMENT = "";
            RED_REPLACEMENT = "";
            GREEN_REPLACEMENT = "";
            BOLD_REPLACEMENT = "";
            RESET_REPLACEMENT = "";
            DIAMOND_REPLACEMENT = "<>";
            BOMB_REPLACEMENT = "o-";
            EXPLOSION_REPLACEMENT = "*";
            FIRE_REPLACEMENT = ".^.";
            GHOST_REPLACEMENT = "^o^";
            SNAKE_REPLACEMENT = "-<_/\\_*";
            CONSTRUCTION_REPLACEMENT = "x";
        }
    }

    public static void print(String format, Object ... args) {
        format = format.replaceAll("\\^cyan\\^", CYAN_REPLACEMENT);
        format = format.replaceAll("\\^yellow\\^", YELLOW_REPLACEMENT);
        format = format.replaceAll("\\^red\\^", RED_REPLACEMENT);
        format = format.replaceAll("\\^green\\^", GREEN_REPLACEMENT);
        format = format.replaceAll("\\^b\\^", BOLD_REPLACEMENT);
        format = format.replaceAll("\\^r\\^", RESET_REPLACEMENT);
        format = format.replaceAll("\\^diamond\\^", DIAMOND_REPLACEMENT);
        format = format.replaceAll("\\^bomb\\^", BOMB_REPLACEMENT);
        format = format.replaceAll("\\^explosion\\^", EXPLOSION_REPLACEMENT);
        format = format.replaceAll("\\^fire\\^", FIRE_REPLACEMENT);
        format = format.replaceAll("\\^ghost\\^", GHOST_REPLACEMENT);
        format = format.replaceAll("\\^snake\\^", SNAKE_REPLACEMENT);
        format = format.replaceAll("\\^construction\\^", CONSTRUCTION_REPLACEMENT);
        System.out.printf(format, args);
    }

}
