package alarmEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class AlarmEntity {
    public static void main(String[] args) {
        AlarmLog gui = new AlarmLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });
        
        AlarmWorker worker = new AlarmWorker(gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Alarm, worker);
        consumer.subscribe(TOPIC_FromDigest_ALARM);
        consumer.run();
    }
}
