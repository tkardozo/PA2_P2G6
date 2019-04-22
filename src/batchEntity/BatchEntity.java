package batchEntity;

import kafka.SimpleConsumer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static common.Properties.*;

public class BatchEntity {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        BatchLog gui = new BatchLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        BatchWorker worker = new BatchWorker(BATCH_File, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Batch, worker);
        consumer.subscribe(TOPIC_FromDigest);
        consumer.run();
    }
}
