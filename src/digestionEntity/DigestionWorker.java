package digestionEntity;

import common.Entry;
import kafka.Callback;
import kafka.SimpleProducer;
import kafka.SimpleProperties;

import static common.Properties.*;

/**
 * Callback for the KAFKA consumer of the AlarmEntity
 *
 * @see AlarmEntity
 * @author P2G6
 */
public class DigestionWorker implements Callback<String> {

    private SimpleProducer<String> fafProducer;
    private SimpleProducer<String> syncProducer;
    private DigestionLog gui;

    /**
     * Registers the AlarmLog GUI to be updated on callback
     *
     * @param servers The KAFKA server endpoints
     * @param gui The created GUI object
     */
    public DigestionWorker(String servers, DigestionLog gui) {
        this.fafProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_FAF);
        this.syncProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_SYNC);
        this.gui = gui;
    }

    /**
     * Receives each message of the specified topics
     *
     * @param key The message type
     * @param value The full message
     */
    @Override
    public void onReceive(String key, String value) {
        this.gui.log(value, false);
        Entry entry = new Entry(value);

        // Add speed limit if message type id SPEED
        if (key.equals(MSG_SPEED)) {
            entry.add("100");
        }

        // Register all messages
        entry.register();
        String result = entry.toString();

        switch (key) {
            case MSG_HEARTBEAT:
                // HEARTBEAT messages can be sent with "fire and forget" to the BatchEntity and ReportEntity
                fafProducer.send(TOPIC_FromDigestion, key, result);
                this.gui.log("FAF/" + TOPIC_FromDigestion + ": " + result);
                break;
            case MSG_SPEED:
                // SPEED messages must be sent with "synchronous" to the AlarmEntity
                syncProducer.send(TOPIC_FromDigest_ALARM, key, result);
                this.gui.log("SYNC/" + TOPIC_FromDigest_ALARM + ": " + result);
            case MSG_STATUS:
                // SPEED and STATUS messages must be sent with "synchronous" to the BatchEntity and ReportEntity
                syncProducer.send(TOPIC_FromDigestion, key, result);
                this.gui.log("SYNC/" + TOPIC_FromDigestion + ":" + result);
                break;
        }
    }
}
