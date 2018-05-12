package edu.nyu.cs9053.homework11.network;

import edu.nyu.cs9053.homework11.game.Difficulty;
import edu.nyu.cs9053.homework11.game.screen.InputMove;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * User: blangel
 * <p>
 * A NIO implementation of a NetworkGameProvider.
 * <p>
 * The server takes the following commands:
 * <pre>
 *     foes Difficulty
 * </pre>
 * <pre>
 *     move
 * </pre>
 * where the String "foes Easy" would be a call to {@linkplain NetworkGameProvider#getRandomNumberOfNextFoes(String)}
 * with "Easy"
 * and a call using String "move" would be a call to {@linkplain NetworkGameProvider#getRandomNextMove()}
 */
public class GameServer implements NetworkGameProvider, Runnable {

    public static final String SERVER_HOST = "localhost";

    public static final int SERVER_PORT = 8080;

    public static final int READ_BUFFER_SIZE = 128;

    public static final int WRITE_BUFFER_SIZE = 128;

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer();
        server.run();
    }

    private final Map<SelectableChannel, String> mapBuffers;

    private final ServerSocketChannel serverChannel;

    private final Selector selector;

    private final ByteBuffer readBuffer;

    private final ByteBuffer writeBuffer;

    private final Random random;

    public GameServer() throws IOException {
        this.selector = Selector.open();
        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.configureBlocking(false);
        this.serverChannel.socket().bind(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        this.serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        this.mapBuffers = new HashMap<>();
        this.readBuffer = ByteBuffer.allocate(READ_BUFFER_SIZE);
        this.writeBuffer = ByteBuffer.allocate(WRITE_BUFFER_SIZE);
        this.random = new Random();
    }

    @Override
    public String getRandomNumberOfNextFoes(String difficulty) {
        switch (difficulty) {
            case "1":
                return String.valueOf(random.nextInt(Difficulty.Easy.getLevel() + 1));
            case "2":
                return String.valueOf(random.nextInt(Difficulty.Medium.getLevel() + 1));
            case "3":
                return String.valueOf(random.nextInt(Difficulty.Hard.getLevel() + 1));
            default:
                throw new AssertionError("Difficulty must be Easy, Medium or Hard");
        }
    }

    @Override
    public String getRandomNextMove() {
        if (random.nextInt(100) < 50) {
            if (random.nextInt(100) < 50) {
                return InputMove.Up.toString();
            } else {
                return InputMove.Down.toString();
            }
        } else {
            if (random.nextInt(100) < 5) {
                return InputMove.Right.toString();
            } else {
                return InputMove.Left.toString();
            }
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                handle();
            } catch (IOException ioe) {
                System.out.printf("Exception - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handle() throws IOException {
        if (selector.select() < 1) {
            return;
        }
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isAcceptable()) {
                accept();
            } else if (key.isReadable()) {
                read(key);
            } else if (key.isWritable()) {
                write(key);
            }
            keyIterator.remove();
        }
    }

    private void accept() throws IOException {
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        readBuffer.clear();
        channel.read(readBuffer);
        readBuffer.flip();
        String request = byteToString(readBuffer);
        if (request.contains("FoesOrMoves")) {
            mapBuffers.put(channel, convertAndSaveMessage(request));
        }
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        String message = mapBuffers.get(channel);
        if (message != null) {
            mapBuffers.remove(channel);
            writeBuffer.clear();
            writeBuffer.put(processRequest(message).getBytes());
            writeBuffer.flip();
            while (writeBuffer.hasRemaining()) {
                channel.write(writeBuffer);
            }
        }
    }

    private String byteToString(ByteBuffer buffer) {
        byte[] bytes;
        if (buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            buffer.rewind();
            bytes = new byte[buffer.remaining()];
        }
        return new String(bytes, GameClient.CHARSETS);
    }

    private String convertAndSaveMessage(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(message.split("\n")[0].substring(message.indexOf(":") + 1, message.indexOf(",")) + ":");
        message = message.split("\n")[0].split(",")[1];
        sb.append(message.substring(message.indexOf(":") + 1, message.indexOf("}")));
        return sb.toString();
    }

    private String processRequest(String message) throws IOException {
        String response;
        if (GameClient.RANDOM_NEXT_MOVE.equals(message.split(":")[0])) {
            response = String.format(getRandomNextMove() + "%n");
        } else {
            response = String.format(getRandomNumberOfNextFoes(message.split(":")[1]) + "%n");
        }
        return response;
    }
}