package alarmEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

/**
 * The entity responsible for checking the speed of each car, activating an
 * alarm if over the limit
 *
 * @author P2G6
 */
public class AlarmEntity {

    /**
     * Main execution of the entity
     *
     * @param args
     */
    public static void main(String[] args) {
        // creating and running the gui
        AlarmLog gui = new AlarmLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        // creating, configuring and running the worker
        AlarmWorker worker = new AlarmWorker(gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Alarm, worker);
        consumer.subscribe(TOPIC_FromDigest_ALARM);
        consumer.run();
    }
}
