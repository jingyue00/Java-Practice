package edu.nyu.cs9053.homework11.network;

import edu.nyu.cs9053.homework11.game.Difficulty;
import edu.nyu.cs9053.homework11.game.GameProvider;
import edu.nyu.cs9053.homework11.game.screen.InputMove;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * User: blangel
 * <p>
 * A blocking IO implementation of a client which requests moves from a remote server implementing the
 * {@linkplain edu.nyu.cs9053.homework11.network.NetworkGameProvider}
 */
public class GameClient implements GameProvider {

    public static final String RANDOM_NEXT_FOES = "NEXT_FOES";

    public static final String RANDOM_NEXT_MOVE = "NEXT_MOVE";

    public static final Charset CHARSETS = StandardCharsets.UTF_8;

    public static GameClient construct(Difficulty difficulty) {
        try {
            Socket serverConnection = new Socket(GameServer.SERVER_HOST, GameServer.SERVER_PORT);
            return new GameClient(difficulty, serverConnection.getInputStream(), serverConnection.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect server!");
        }
    }

    private final Difficulty difficulty;

    private final InputStream inputStream;

    private final OutputStream outputStream;

    public GameClient(Difficulty difficulty, InputStream serverInput, OutputStream serverOutput) {
        this.difficulty = difficulty;
        this.inputStream = serverInput;
        this.outputStream = serverOutput;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public int getRandomNumberOfNextFoes() {
        return Integer.valueOf(process(RANDOM_NEXT_FOES));
    }

    @Override
    public InputMove getRandomNextMove() {
        switch (process(RANDOM_NEXT_MOVE)) {
            case "Up":
                return InputMove.Up;
            case "Down":
                return InputMove.Down;
            case "Left":
                return InputMove.Left;
            case "Right":
                return InputMove.Right;
            default:
                throw new AssertionError("Input Moves must be Up, Down, Left or Right!");
        }
    }

    private String process(String FoesOrMoves) {
        PrintWriter writer = new PrintWriter(this.outputStream, true);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(this.inputStream, CHARSETS));
        String randomFoesOrMoves;
        try {
            formatSendMessage(writer, FoesOrMoves, difficulty.getLevel());
            randomFoesOrMoves = fileReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed - random_next_foes");
        }
        return randomFoesOrMoves;
    }

    private void formatSendMessage(PrintWriter out, String foesOrMoves, int difficulty) {
        out.println(String.format("{FoesOrMoves:%s, difficulty:%d}%n", foesOrMoves, difficulty));
    }
}