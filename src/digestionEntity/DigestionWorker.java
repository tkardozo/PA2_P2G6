package digestionEntity;

import common.Entry;
import kafka.Callback;
import kafka.SimpleProducer;
import kafka.SimpleProperties;

import static common.Properties.*;

public class DigestionWorker implements Callback<String> {
    private SimpleProducer<String> fafProducer;
    private SimpleProducer<String> syncProducer;

    public DigestionWorker(String servers){
        this.fafProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_FAF);
        this.syncProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_SYNC);
    }

    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);
        Entry entry = new Entry(value);

        if(key.equals(MSG_SPEED))
            entry.add("100");

        entry.register();
        String result = entry.toString();

        switch(key){
            case MSG_HEARTBEAT:
                fafProducer.send(TOPIC_FromDigest, key, result);
                System.out.println("\tSent (via FAF - " + TOPIC_FromDigest + "): " + result);
                break;
            case MSG_SPEED:
                syncProducer.send(TOPIC_FromDigest_ALARM, key, result);
                System.out.println("\tSent (via SYNC - " + TOPIC_FromDigest_ALARM + ": " + result);
            case MSG_STATUS:
                syncProducer.send(TOPIC_FromDigest, key, result);
                System.out.println("\tSent (via SYNC - " + TOPIC_FromDigest + ": " + result);
                break;
        }
    }
}
