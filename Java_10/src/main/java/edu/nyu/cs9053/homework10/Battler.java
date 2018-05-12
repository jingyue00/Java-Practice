package edu.nyu.cs9053.homework10;

/**
 * User: blangel
 */
public class Battler {

    private static final String USAGE =
            "Usage: Battler type factor\n" +
                    "  where 'type' is either 'middle-ages' or 'modern'\n" +
                    "  and   'factor' is the concurrency factor (i.e., number of threads to use when processing); must be at least 5";

    public static void main(String[] args) {
        if ((args == null) || (args.length != 2) ||
                (!"middle-ages".equals(args[0]) && !"modern".equals(args[0]))) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        int concurrencyFactor;
        try {
            concurrencyFactor = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        if (concurrencyFactor < 5) {
            System.out.printf("%s%n", USAGE);
            return;
        }
        Fortification<?> fortification;
        if ("middle-ages".equals(args[0])) {
            fortification = FortificationFactory.createMiddleAges(concurrencyFactor);
        } else {
            fortification = FortificationFactory.createModern(concurrencyFactor);
        }
        Invasion invasion = new Invasion(fortification, concurrencyFactor);
        invasion.start();
    }

}
