package digestionEntity;

import kafka.SimpleConsumer;

import static common.Properties.SERVERS;
import static common.Properties.TOPIC_TO_Digest;

public class DigestionEntity {
    public static void main(String[] args) {
        DigestionWorker worker = new DigestionWorker(SERVERS);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, "digestion", worker);
        consumer.subscribe(TOPIC_TO_Digest);
        consumer.run();
    }

}
