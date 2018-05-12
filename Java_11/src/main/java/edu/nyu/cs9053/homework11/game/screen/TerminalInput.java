package edu.nyu.cs9053.homework11.game.screen;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public class TerminalInput implements Runnable {

    private final AtomicReference<String> ttyConfig;

    private final BlockingQueue<InputMove> latestMove;

    public TerminalInput(BlockingQueue<InputMove> latestMove) {
        this.latestMove = latestMove;
        this.ttyConfig = new AtomicReference<>();
    }

    @Override public void run() {
        try {
            setTerminalToCBreak();
            while (!Thread.currentThread().isInterrupted()) {
                if (System.in.available() != 0) {
                    int character = System.in.read();
                    InputMove move = InputMove.getMove(character);
                    if (move != null) {
                        latestMove.put(move);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        } finally {
            revert();
        }
    }

    public void revert() {
        try {
            stty(ttyConfig.get().trim());
            stty("echo");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setTerminalToCBreak() throws IOException, InterruptedException {
        ttyConfig.set(stty("-g"));
        // set the console to be character-buffered instead of line-buffered
        stty("-icanon min 1");
        // disable character echoing
        stty("-echo");
    }

    /**
     * Execute the stty command with the specified arguments
     * against the current active terminal.
     */
    private String stty(final String args) throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";
        return exec(new String[]{
                "sh",
                "-c",
                cmd
        });
    }

    private String exec(final String[] cmd) throws IOException, InterruptedException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ProcessBuilder processBuilder = new ProcessBuilder(cmd).redirectErrorStream(true);
        Process process = processBuilder.start();
        int character;
        InputStream input = process.getInputStream();
        while ((character = input.read()) != -1) {
            stream.write(character);
        }
        process.waitFor();
        return new String(stream.toByteArray());
    }

}