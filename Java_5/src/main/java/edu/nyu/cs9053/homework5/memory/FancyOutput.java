package edu.nyu.cs9053.homework5.memory;

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

    private static final String RESET_REPLACEMENT;

    private static final String WATER_REPLACEMENT;

    static {
        String terminal = System.getenv("TERM");
        if (terminal != null) {
            CYAN_REPLACEMENT = "\u001b[1;36m";
            YELLOW_REPLACEMENT = "\u001b[1;33m";
            RED_REPLACEMENT = "\u001b[1;31m";
            GREEN_REPLACEMENT = "\u001b[1;32m";
            RESET_REPLACEMENT = "\u001b[0m";
            WATER_REPLACEMENT = "\uD83D\uDCA7";
        } else {
            CYAN_REPLACEMENT = "";
            YELLOW_REPLACEMENT = "";
            RED_REPLACEMENT = "";
            GREEN_REPLACEMENT = "";
            RESET_REPLACEMENT = "";
            WATER_REPLACEMENT = ".";
        }
    }

    public static void print(String format, Object... args) {
        format = format.replaceAll("\\^cyan\\^", CYAN_REPLACEMENT);
        format = format.replaceAll("\\^yellow\\^", YELLOW_REPLACEMENT);
        format = format.replaceAll("\\^red\\^", RED_REPLACEMENT);
        format = format.replaceAll("\\^green\\^", GREEN_REPLACEMENT);
        format = format.replaceAll("\\^r\\^", RESET_REPLACEMENT);
        format = format.replaceAll("\\^water\\^", WATER_REPLACEMENT);
        System.out.printf(format, args);
    }

}
