package alarmEntity;

import common.Entry;
import kafka.Callback;

/**
 * Callback for the KAFKA consumer of the AlarmEntity
 *
 * @see AlarmEntity
 * @author P2G6
 */
public class AlarmWorker implements Callback<String> {

    private AlarmLog gui;

    /**
     * Registers the AlarmLog GUI to be updated on callback
     *
     * @param gui The created GUI object
     */
    public AlarmWorker(AlarmLog gui) {
        this.gui = gui;
    }

    /**
     * Receives each message of the specified topics
     *
     * @param key The message type, in this case should only receive "01 -
     * SPEED"
     * @param value The full message
     */
    @Override
    public void onReceive(String key, String value) {
        Entry entry = new Entry(value);

        Integer speed = Integer.parseInt(entry.get(4));
        Integer maxSpeed = Integer.parseInt(entry.get(6));

        // add the processed status to the message
        if (speed > maxSpeed) {
            entry.add("ON");
        } else {
            entry.add("OFF");
        }

        // logs the message on the gui
        this.gui.log(entry.toString());
    }
}
