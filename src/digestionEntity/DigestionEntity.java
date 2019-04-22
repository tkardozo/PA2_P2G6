package digestionEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class DigestionEntity {
    public static void main(String[] args) {
        DigestionLog gui = new DigestionLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });
        
        DigestionWorker worker = new DigestionWorker(SERVERS, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Digestion, worker);
        consumer.subscribe(TOPIC_ToDigest);
        consumer.run();
    }

}
