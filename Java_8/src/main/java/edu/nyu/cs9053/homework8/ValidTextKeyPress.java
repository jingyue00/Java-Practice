package edu.nyu.cs9053.homework8;

public enum ValidTextKeyPress{

    Two("2"),

    Three("3"),

    Four("4"),

    Five("5"),

    Six("6"),

    Seven("7"),

    Eight("8"),

    Nine("9");

    private final String prefix;

    ValidTextKeyPress(String prefix) {
        this.prefix = prefix;
    }

    public String getValidTextPress() {
        return prefix;
    }
}