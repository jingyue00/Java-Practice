package edu.nyu.cs9053.homework11.game;

import edu.nyu.cs9053.homework11.game.screen.Color;
import edu.nyu.cs9053.homework11.game.screen.Decoration;
import edu.nyu.cs9053.homework11.game.screen.InputMove;
import edu.nyu.cs9053.homework11.game.screen.TerminalInput;
import edu.nyu.cs9053.homework11.network.GameClient;
import net.ocheyedan.Characteristic;
import net.ocheyedan.Emoji;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * User: blangel
 */
public class Game {

    private static final List<Emoji> FOES = filter(Emoji.values());

    public static void main(String[] args) {
        Difficulty difficulty = parseDifficulty(args);
        Board board = new Board(new Boundary(new Coordinates.Default(1, 1), new Coordinates.Default(200, 48)), System.out);
        PlayerChooser playerChooser = new PlayerChooser(board);
        final Player player = playerChooser.getPlayer();
        final ArrayBlockingQueue<InputMove> input = new ArrayBlockingQueue<>(1);
        final Game game = new Game(GameClient.construct(difficulty), player, new Board(System.out));
        final TerminalInput terminalInput = new TerminalInput(input);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override public void run() {
                reset(terminalInput, game);
            }
        }));
        Thread inputThread = new Thread(new Runnable() {
            @Override public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        terminalInput.run();
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        });
        inputThread.start();
        final CountDownLatch latch = new CountDownLatch(1);
        Thread gameThread = new Thread(new Runnable() {
            @Override public void run() {
                try {
                    game.start(input);
                    latch.countDown();
                } catch (Exception e) {
                    System.out.printf("Error - %s%n", e.getMessage());
                }
            }
        });
        gameThread.start();
        try {
            latch.await();
            inputThread.interrupt();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }

    private static Difficulty parseDifficulty(String[] args) {
        if ((args == null) || (args.length != 1)) {
            return Difficulty.Easy;
        }
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(args[0]);
        } catch (Exception e) {
            difficulty = Difficulty.Easy;
        }
        return difficulty;
    }

    private static void reset(TerminalInput input, Game game) {
        input.revert();
        game.reset();
    }

    private static List<Emoji> filter(Emoji[] emoji) {
        Collection<Emoji> filtered = new LinkedList<>();
        for (Emoji toFilter : emoji) {
            for (Characteristic characteristic : toFilter.getCharacteristics()) {
                if (characteristic == Characteristic.Animal) {
                    filtered.add(toFilter);
                }
            }
        }
        return new ArrayList<>(filtered);
    }

    private final GameProvider gameProvider;

    private final Player player;

    private final Board board;

    private final Collection<Piece> swarm;

    private final Random random;

    public Game(GameProvider gameProvider, Player player, Board board) {
        this.gameProvider = gameProvider;
        this.player = player;
        this.board = board;
        this.swarm = new LinkedList<>();
        this.random = new Random();
    }

    public void start(BlockingQueue<InputMove> input) {
        Board inner = board.inner();
        Piece piece = new Piece(player.getEmoji(), 2, 25, inner.getBoundary());
        board.clear();
        board.printBorder();
        board.print((Piece) null, piece);
        while (!Thread.currentThread().isInterrupted()) {
            InputMove move = getNextMove(input);
            if (move == null) {
                continue;
            }
            Piece existing = piece;
            piece = piece.move(move);
            inner.print(existing, piece);
            updateSwarm(inner);
            if (checkForCollision(piece)) {
                handleCollision();
                break;
            } else if (checkForWin(piece, inner)) {
                handleWin();
                break;
            }
        }
    }

    private InputMove getNextMove(BlockingQueue<InputMove> input) {
        try {
            return input.take();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    private void updateSwarm(Board board) {
        int newFoes = gameProvider.getRandomNumberOfNextFoes();
        for (int i = 0; i < newFoes; i++) {
            swarm.add(generate(board.getBoundary().getMaximum().getX(), board));
        }
        InputMove move = gameProvider.getRandomNextMove();
        Collection<Piece> updated = new LinkedList<>();
        for (Piece foe : swarm) {
            Piece existingFoe = foe;
            foe = foe.move(move);
            board.print(existingFoe, foe);
            if (foe.getY() != 1) {
                updated.add(foe);
            }
        }
        swarm.clear();
        swarm.addAll(updated);
    }

    private Piece generate(int x, Board board) {
        Emoji emoji = FOES.get(random.nextInt(FOES.size()));
        return new Piece(emoji, x, random.nextInt(board.getBoundary().getMaximum().getY()) + 1, board.getBoundary());
    }

    private boolean checkForCollision(Piece piece) {
        for (Piece foe : swarm) {
            if ((piece.getX() == foe.getX()) && (piece.getY() == foe.getY())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForWin(Piece piece, Board board) {
        return (piece.getX() == board.getBoundary().getMaximum().getX());
    }

    private void handleCollision() {
        handle("Sorry, better luck next time...", Emoji.Skull, Color.Red);
    }

    private void handleWin() {
        handle("You've won! Congratulations!", Emoji.Trophy, Color.Magenta);
    }

    private void handle(String message, Emoji border, Color foreground) {
        board.clear();
        Board error = new Board(new Boundary(new Coordinates.Default(1, 1), new Coordinates.Default(35, 5)), System.out);
        error.printBorder(border);
        error.print(message, Decoration.Blinking, Color.Black, foreground, new Coordinates.Default(4, 3));
    }

    public void reset() {
        board.reset();
    }

}
