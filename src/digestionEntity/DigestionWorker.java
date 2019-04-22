package digestionEntity;

import common.Entry;
import kafka.Callback;
import kafka.SimpleProducer;
import kafka.SimpleProperties;

import static common.Properties.*;

public class DigestionWorker implements Callback<String> {
    private SimpleProducer<String> fafProducer;
    private SimpleProducer<String> syncProducer;
    private DigestionLog gui;

    public DigestionWorker(String servers, DigestionLog gui){
        this.fafProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_FAF);
        this.syncProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_SYNC);
        this.gui = gui;
    }

    @Override
    public void onSuccess(String key, String value) {
        this.gui.log(value, false);
        Entry entry = new Entry(value);

        if(key.equals(MSG_SPEED))
            entry.add("100");

        entry.register();
        String result = entry.toString();

        switch(key){
            case MSG_HEARTBEAT:
                fafProducer.send(TOPIC_FromDigest, key, result);
                this.gui.log("FAF/" + TOPIC_FromDigest + ": " + result);
                break;
            case MSG_SPEED:
                syncProducer.send(TOPIC_FromDigest_ALARM, key, result);
                this.gui.log("SYNC/" + TOPIC_FromDigest_ALARM + ": " + result);
            case MSG_STATUS:
                syncProducer.send(TOPIC_FromDigest, key, result);
                this.gui.log("SYNC/" + TOPIC_FromDigest + ":" + result);
                break;
        }
    }
}
