package edu.nyu.cs9053.homework11.game;

import edu.nyu.cs9053.homework11.game.screen.InputMove;
import net.ocheyedan.Emoji;

/**
 * User: blangel
 */
public class Piece implements Coordinates {

    private final String codePoint;

    private final int x;

    private final int y;

    private final Boundary boundary;

    public Piece(Emoji emoji, int x, int y, Boundary boundary) {
        this(emoji.getCodePoint(), x, y, boundary);
    }

    protected Piece(String codePoint, int x, int y, Boundary boundary) {
        this.codePoint = codePoint;
        this.x = x;
        this.y = y;
        this.boundary = boundary;
    }

    public Piece move(InputMove move) {
        if (move == null) {
            throw new IllegalArgumentException();
        }
        switch (move) {
            case Up:
                return new Piece(codePoint, x, Math.max(boundary.getMinimum().getY(), y - 1), boundary);
            case Down:
                return new Piece(codePoint, x, Math.min(boundary.getMaximum().getY(), y + 1), boundary);
            case Right:
                return new Piece(codePoint, Math.min(boundary.getMaximum().getX(), x + 1), y, boundary);
            case Left:
                return new Piece(codePoint, Math.max(boundary.getMinimum().getX(), x - 1), y, boundary);
            default:
                throw new AssertionError();
        }
    }

    public String getCodePoint() {
        return codePoint;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Piece other = (Piece) o;

        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return (codePoint == null ? (other.codePoint == null) : codePoint.equals(other.codePoint));
    }

    @Override public int hashCode() {
        int result = codePoint != null ? codePoint.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

}
