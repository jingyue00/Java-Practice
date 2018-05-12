package edu.nyu.cs9053.homework6;

import java.util.Random;

/**
 * User: blangel
 */
public class Mine {

    // Unnecessary to modify this method, so do not.
    public static void main(String[] args) {
        if ((args == null) || (args.length != 1)) {
            throw new IllegalArgumentException();
        }
        Mine mine = new Mine(new SafetyDepositBox(Long.valueOf(args[0])));
        mine.mine();
    }

    private final SafetyDepositBox safetyDepositBox;

    private final Random random;

    public Mine(SafetyDepositBox safetyDepositBox) {
        this.safetyDepositBox = safetyDepositBox;
        this.random = new Random();
    }

    private void mine() {
        if (!getTools()) {
            throw new IllegalStateException("Could not get mining tools; invalid safe password combination");
        }
        int gemsMined;
        while ((gemsMined = sluice()) == 0) {
            handleNoGems();
        }
        if (!cashIn(gemsMined)) {
            throw new IllegalStateException(String.format("Could not cash in (would have earned %d gems); invalid safe password combination", gemsMined));
        }
        FancyOutput.print("Well done! You've successfully mined ^green^%d^r^ gems!%n", gemsMined);
        FancyOutput.print(getGems(gemsMined));
    }

    private int sluice() {
        if (random.nextBoolean()) {
            return random.nextInt(5);
        }
        return 0;
    }

    @Safe(password = 335487394L)
    private boolean getTools() {
        return safetyDepositBox.canGetTools(this);
    }

    @Safe(password = 2268677828L)
    private boolean cashIn(int gems) {
        return safetyDepositBox.canCashIn(this, gems);
    }

    // Unnecessary to modify this method, so do not.
    private void handleNoGems() {
        FancyOutput.print(String.format("Careful! You just had an accident - %s%n", getAccident()));
        FancyOutput.print("  sleeping it off then retrying...%n");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }

    // Unnecessary to modify this method, so do not.
    private String getAccident() {
        int choice = random.nextInt(4);
        switch (choice) {
            case 0:
                return "^bomb^";
            case 1:
                return "^explosion^";
            case 2:
                return "^fire^";
            default:
                return "^ghost^";
        }
    }

    // Unnecessary to modify this method, so do not.
    private String getGems(int gemsMined) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < gemsMined; i++) {
            buffer.append("^diamond^ ");
        }
        buffer.append("%n");
        return buffer.toString();
    }

}
