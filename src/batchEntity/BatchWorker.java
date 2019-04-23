package batchEntity;

import kafka.Callback;

import java.io.*;

/**
 * Callback for the KAFKA consumer of the BatchEntity
 *
 * @see BatchEntity
 * @author P2G6
 */
public class BatchWorker implements Callback<String> {

    private Writer writer;
    private BatchLog gui;

    /**
     * Creates the file for the output log Registers the BatchLog GUI to be
     * updated on callback
     *
     * @param file The path for the output log
     * @param gui The created GUI object
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public BatchWorker(String file, BatchLog gui) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        this.gui = gui;
    }

    /**
     * Receives each message of the specified topics
     *
     * @param key The message type
     * @param value The full message
     */
    @Override
    public void onReceive(String key, String value) {
        this.gui.log(value);
        // just writing the message to the file
        try {
            writer.write(value + "\n");
        } catch (IOException e) {
        }
    }
}
