package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class AsciiArtPrinter {

    /**
     * @implNote should only print values within {@code asciiArt} and nothing else within this method
     * @param asciiArt to print
     */
    public void print(char[][] asciiArt) {
        for (int i = 0; i < asciiArt.length; i++) {  
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < asciiArt[i].length; j++) {
                sb.append(asciiArt[i][j]);
            }
            System.out.println(sb);
        }
    }

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

}
