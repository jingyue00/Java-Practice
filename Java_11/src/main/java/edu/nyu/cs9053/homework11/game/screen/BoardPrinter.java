package edu.nyu.cs9053.homework11.game.screen;

import edu.nyu.cs9053.homework11.game.Boundary;
import edu.nyu.cs9053.homework11.game.Coordinates;
import edu.nyu.cs9053.homework11.game.Piece;
import net.ocheyedan.Emoji;

import java.io.PrintStream;

/**
 * User: blangel
 */
public class BoardPrinter {

    private final PrintStream stream;

    public BoardPrinter(PrintStream stream) {
        this.stream = stream;
    }

    public void clear() {
        stream.printf("\u001B[2J");
        stream.printf("\u001B[?25l");
    }

    public void clear(Coordinates coordinates) {
        stream.printf("\u001B[%d;%dH", coordinates.getY(), coordinates.getX());
        stream.printf(" ");
    }

    public void reset() {
        stream.printf("\u001B[51;1H");
        enableInput();
    }

    public void print(Piece piece) {
        stream.printf("\u001B[%d;%dH", piece.getY(), piece.getX());
        stream.printf("%s", piece.getCodePoint());
    }

    public void move(Coordinates coordinates) {
        stream.printf("\u001B[%d;%dH", coordinates.getY(), coordinates.getX());
    }

    public void print(String text, Decoration decoration, Color background, Color foreground) {
        stream.printf("\u001B[%d;4%d;3%dm", decoration.getCode(), background.getCode(), foreground.getCode());
        stream.printf(text);
        stream.printf("\u001B[%dm", Decoration.Normal.getCode());
    }

    public void enableInput() {
        stream.printf("\u001B[?25h");
    }

    public void print(Boundary boundary, Emoji emoji, Color background, Color foreground) {
        print(boundary, emoji.getCodePoint(), emoji.getCodePoint(), Decoration.Normal, background, foreground, true);
    }

    public void print(Boundary boundary, String vertical, String horizontal, Decoration decoration, Color background,
                      Color foreground, boolean emoji) {
        int increment = (emoji ? 2 : 1);
        for (int y = boundary.getMinimum().getY(); y < boundary.getMaximum().getY() + 1; y++) {
            for (int x = boundary.getMinimum().getX(); x < boundary.getMaximum().getX() + 1; x += increment) {
                stream.printf("\u001B[%d;%dH", y, x);
                if ((x == boundary.getMinimum().getX()) || (x == boundary.getMaximum().getX())) {
                    stream.printf("\u001B[%d;4%d;3%dm", decoration.getCode(), background.getCode(), foreground.getCode());
                    stream.printf(vertical);
                } else if ((y == boundary.getMinimum().getY()) || (y == boundary.getMaximum().getY())) {
                    stream.printf("\u001B[%d;4%d;3%dm", decoration.getCode(), background.getCode(), foreground.getCode());
                    stream.printf(horizontal);
                } else {
                    stream.printf("\u001B[%dm", decoration.getCode());
                    stream.printf(" ");
                }
            }
            stream.printf("%n");
        }
        stream.printf("\u001B[%dm", decoration.getCode());
    }

}
