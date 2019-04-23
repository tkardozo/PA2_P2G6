package batchEntity;

import kafka.SimpleConsumer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static common.Properties.*;

/**
 * The entity responsible for saving the messages in a file
 *
 * @author P2G6
 */
public class BatchEntity {

    /**
     * Main execution of the entity
     *
     * @param args
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // creating and running the gui
        BatchLog gui = new BatchLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        // creating, configuring and running the worker
        BatchWorker worker = new BatchWorker(BATCH_File, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Batch, worker);
        consumer.subscribe(TOPIC_FromDigestion);
        consumer.run();
    }
}
