package edu.nyu.cs9053.homework11.game;

/**
 * User: blangel
 */
public interface Coordinates {

    public static class Default implements Coordinates {

        private final int x;

        private final int y;

        public Default(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override public int getX() {
            return x;
        }

        @Override public int getY() {
            return y;
        }
    }

    int getX();

    int getY();

}
