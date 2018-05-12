package edu.nyu.cs9053.homework11.game;

import edu.nyu.cs9053.homework11.game.screen.BoardPrinter;
import edu.nyu.cs9053.homework11.game.screen.Color;
import edu.nyu.cs9053.homework11.game.screen.Decoration;
import net.ocheyedan.Emoji;

import java.io.PrintStream;

/**
 * User: blangel
 */
public class Board {

    private final Boundary boundary;

    private final BoardPrinter boardPrinter;

    public Board(PrintStream printStream) {
        this(new Boundary(new Coordinates.Default(1, 1), new Coordinates.Default(151, 48)), printStream);
    }
    public Board(Boundary boundary, PrintStream printStream) {
        this.boundary = boundary;
        this.boardPrinter = new BoardPrinter(printStream);
    }

    private Board(Boundary boundary, BoardPrinter boardPrinter) {
        this.boundary = boundary;
        this.boardPrinter = boardPrinter;
    }

    public Board inner() {
        return new Board(boundary.decrease(), boardPrinter);
    }

    public Boundary getBoundary() {
        return boundary;
    }

    public void clear() {
        boardPrinter.clear();
    }

    public void reset() {
        boardPrinter.reset();
    }

    public void enableInput() {
        boardPrinter.enableInput();
    }

    public void printBorder() {
        printBorder(Emoji.EightPointedBlackStar);
    }

    public void printBorder(Emoji border) {
        boardPrinter.print(boundary, border, Color.Black, Color.White);
    }

    public void print(String text, Coordinates coordinates) {
        print(text, Decoration.Bold, Color.Black, Color.Cyan, coordinates);
    }

    public void print(String text, Decoration decoration, Color background, Color foreground, Coordinates coordinates) {
        boardPrinter.move(coordinates);
        boardPrinter.print(text, decoration, background, foreground);
    }

    public void print(Piece existing, Piece piece) {
        if (existing != null) {
            boardPrinter.clear(existing);
        }
        boardPrinter.print(piece);
    }

}
