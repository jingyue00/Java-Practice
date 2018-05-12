package edu.nyu.cs9053.homework11.game;

/**
 * User: blangel
 */
public class Boundary {

    private final Coordinates minimum;

    private final Coordinates maximum;

    public Boundary(Coordinates minimum, Coordinates maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Boundary decrease() {
        return new Boundary(new Coordinates.Default(minimum.getX() + 1, minimum.getY() + 1),
                            new Coordinates.Default(maximum.getX() - 1, maximum.getY() - 1));
    }

    public Coordinates getMinimum() {
        return minimum;
    }

    public Coordinates getMaximum() {
        return maximum;
    }

}
