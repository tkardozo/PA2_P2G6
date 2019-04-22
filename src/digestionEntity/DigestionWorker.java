package digestionEntity;

import common.Entry;
import kafka.Callback;
import kafka.SimpleProducer;
import kafka.SimpleProperties;

import static common.Properties.TOPIC_TO_Batch;
import static common.Properties.TOPIC_TO_Alarm;
import static common.Properties.TOPIC_TO_Report;

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

        if(key.equals("01"))
            entry.add("100");

        entry.register();
        String result = entry.toString();

        fafProducer.send(TOPIC_TO_Batch, key, result);
        System.out.println("\tSent (via FAF): " + result);

        syncProducer.send(TOPIC_TO_Report, key, result);
        System.out.println("\tSent (via SYNC): " + result);

        if(key.equals("01")) {
            syncProducer.send(TOPIC_TO_Alarm, key, result);
            System.out.println("\tSent (via SYNC): " + result);
        }
    }
}
