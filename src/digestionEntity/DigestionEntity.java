package digestionEntity;

import kafka.SimpleConsumer;

import static kafka.common.SERVERS;
import static kafka.common.TOPIC_TO_Digest;

public class DigestionEntity {
    public static void main(String[] args) {
        DigestionWorker worker = new DigestionWorker(SERVERS);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, worker);
        consumer.subscribe(TOPIC_TO_Digest);
        consumer.run();
    }

}
