package batchEntity;

import kafka.Callback;

import java.io.*;

public class BatchWorker implements Callback<String> {

    private Writer writer;
    public BatchWorker (String file) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
    }

    @Override
    public void onSuccess(String key, String value) {

        System.out.println("Received: " + value);

        //gui
        //textArea.append(value + "\n");

        try {
            writer.write(value + "\n");
        } catch (IOException e) {
        }
    }

}
