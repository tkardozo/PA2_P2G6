package alarmEntity;

import common.Entry;
import java.util.HashMap;
import java.util.Map;
import kafka.Callback;

/**
 * Callback for the KAFKA consumer of the AlarmEntity
 *
 * @see AlarmEntity
 * @author P2G6
 */
public class AlarmWorker implements Callback<String> {
    private AlarmLog gui;
    private Map<String, String> carOverSpeed;

    /**
     * Registers the AlarmLog GUI to be updated on callback
     *
     * @param gui The created GUI object
     */
    public AlarmWorker(AlarmLog gui) {
        this.gui = gui;
        this.carOverSpeed = new HashMap<>();
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

        String car = entry.get(0);
        Integer speed = Integer.parseInt(entry.get(4));
        Integer maxSpeed = Integer.parseInt(entry.get(6));
        
        String currentState = (speed > maxSpeed) ? "ON" : "OFF";
        String lastState = this.carOverSpeed.get(car);
        
        if((lastState != null) && (!lastState.equals(currentState))){
            // add the processed status to the message
            entry.add(currentState);
            // logs the message on the gui
            this.gui.log(entry.toString());
        }

        this.carOverSpeed.put(car, currentState);
    }
}
