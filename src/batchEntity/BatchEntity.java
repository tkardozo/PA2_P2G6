package batchEntity;

import kafka.SimpleConsumer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static kafka.common.SERVERS;
import static kafka.common.TOPIC_TO_Batch;

public class BatchEntity {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        BatchWorker worker = new BatchWorker("src/data/batchLogs.txt");
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, worker);
        consumer.subscribe(TOPIC_TO_Batch);
        consumer.run();
    }

}
