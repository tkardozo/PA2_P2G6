package batchEntity;

import kafka.SimpleConsumer;

import static kafka.common.SERVERS;
import static kafka.common.TOPIC_TO_Batch;

public class BatchEntity {
    public static void main(String[] args) {
        BatchWorker worker = new BatchWorker();
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, worker);
        consumer.subscribe(TOPIC_TO_Batch);
        consumer.run();
    }

}
