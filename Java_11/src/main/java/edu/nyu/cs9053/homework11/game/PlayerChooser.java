package edu.nyu.cs9053.homework11.game;

import edu.nyu.cs9053.homework11.game.screen.Color;
import edu.nyu.cs9053.homework11.game.screen.Decoration;
import net.ocheyedan.Characteristic;
import net.ocheyedan.Emoji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * User: blangel
 */
public class PlayerChooser {

    private final Board board;

    public PlayerChooser(Board board) {
        this.board = board;
    }

    public Player getPlayer() {
        board.clear();
        Boundary innerBoundary = board.inner().getBoundary();
        int id = 1, x = 1, y = 1;
        Map<Integer, Emoji> mapping = new HashMap<>();
        for (Emoji emoji : Emoji.values()) {
            if (ignore(emoji)) {
                continue;
            }
            if (x >= innerBoundary.getMaximum().getX()) {
                y += 2;
                x = 1;
            }
            int length = String.valueOf(id).length();
            mapping.put(id, emoji);
            board.print(String.format("%d. ", id++), Decoration.Normal, Color.Black, Color.White, new Coordinates.Default(x, y));
            x += length + 2;
            board.print((Piece) null, new Piece(emoji, x, y, innerBoundary));
            x += 4;
        }
        board.print("Choose your player...", new Coordinates.Default(1, y + 1));
        board.enableInput();
        Integer entered = getInput();
        Emoji emoji = (entered == null ? null : mapping.get(entered));
        if (emoji == null) {
            return getPlayer();
        }
        return new Player(emoji);
    }

    private Integer getInput() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String given = buffer.readLine();
            return Integer.parseInt(given);
        } catch (IOException ioe) {
            return null;
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    @SuppressWarnings("fallthrough")
    private boolean ignore(Emoji emoji) {
        if ((emoji == Emoji.Ghost) || (emoji == Emoji.Alien)) {
            return false;
        }
        for (Characteristic character : emoji.getCharacteristics()) {
            if ((character == Characteristic.Animal) || (character == Characteristic.Person)
                || (character == Characteristic.Plant) || (character == Characteristic.Food)
                || (character == Characteristic.Face)) {
                return false;
            }
        }
        return true;
    }

}
