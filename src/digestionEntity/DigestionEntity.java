package digestionEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class DigestionEntity {
    public static void main(String[] args) {
        DigestionWorker worker = new DigestionWorker(SERVERS);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Digestion, worker);
        consumer.subscribe(TOPIC_ToDigest);
        consumer.run();
    }

}
