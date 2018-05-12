package edu.nyu.cs9053.homework6;

import java.io.IOException;
import java.nio.file.*;

/**
 * User: blangel
 *
 * Unnecessary to modify this file, so do not.
 *
 */
public class Prospector {

    public static void main(String[] args) {
        if (!validArguments(args)) {
            usage();
            return;
        }
        Prospector prospector = new Prospector(args[0]);
        prospector.prospect();
    }

    private static boolean validArguments(String[] args) {
        return ((args != null) && (args.length == 1) && (args[0] != null));
    }

    private static void usage() {
        FancyOutput.print("^cyan^Prospector Usage^r^%n");
        FancyOutput.print("  java edu.nyu.cs9053.homework6.Prospector ^b^netid^r^%n");
        FancyOutput.print("    where ^b^netid^r^ is your NYU Net ID.%n");
    }

    private final String netId;

    private final long prospectorId;

    private Prospector(String netId) {
        this.netId = netId;
        this.prospectorId = CRC32s.crc32(netId);
    }

    private void prospect() {
        if (!validGeography()) {
            FancyOutput.print("You're prospecting in some dangerous areas. Find a better location to prospect. ^snake^%n");
            return;
        }
        if (alreadyMined()) {
            FancyOutput.print("You're prospecting in an already mined area. ^construction^%n");
            return;
        }
        generateMine();
        FancyOutput.print("Well done prospector ^b^%s^r^, you have a found a promising area to mine.%n", netId);
        FancyOutput.print("You have been assigned the following safe password; take note of it - ^green^%s^r^%n", prospectorId);
        FancyOutput.print("You need to now build the mine; java edu.nyu.cs9053.homework6.Mine%n");
    }

    private boolean validGeography() {
        String invocationDirectory = System.getProperty("user.dir");
        if ((invocationDirectory == null) || !invocationDirectory.endsWith("week6")) {
            return false;
        }
        Path expectedPath = Paths.get(invocationDirectory, "src", "main", "java", "edu", "nyu", "cs9053", "homework6");
        return Files.exists(expectedPath);
    }

    private boolean alreadyMined() {
        String invocationDirectory = System.getProperty("user.dir");
        Path expectedMinePath = Paths.get(invocationDirectory, "src", "main", "java", "edu", "nyu", "cs9053", "homework6", "Mine.java");
        Path expectedSafePath = Paths.get(invocationDirectory, "src", "main", "java", "edu", "nyu", "cs9053", "homework6", "Safe.java");
        return Files.exists(expectedMinePath) || Files.exists(expectedSafePath);
    }

    private void generateMine() {
        String invocationDirectory = System.getProperty("user.dir");
        try {
            Files.copy(Paths.get(invocationDirectory, "src", "main", "resources", "Mine.txt"),
                    Paths.get(invocationDirectory, "src", "main", "java", "edu", "nyu", "cs9053", "homework6", "Mine.java"));
            Files.copy(Paths.get(invocationDirectory, "src", "main", "resources", "Safe.txt"),
                    Paths.get(invocationDirectory, "src", "main", "java", "edu", "nyu", "cs9053", "homework6", "Safe.java"));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
