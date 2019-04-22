package batchEntity;

import kafka.Callback;

import java.io.*;

public class BatchWorker implements Callback<String> {
    private Writer writer;
    private BatchLog gui;

    public BatchWorker(String file, BatchLog gui) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        this.gui = gui;
    }

    @Override
    public void onSuccess(String key, String value) {
        this.gui.log(value);
        try {
            writer.write(value + "\n");
        } catch (IOException e) {}
    }
}
