package digestionEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

/**
 * The entity responsible for registering and distributing the messages to the
 * final entities
 *
 * @author P2G6
 */
public class DigestionEntity {

    /**
     * Main execution of the entity
     *
     * @param args
     */
    public static void main(String[] args) {
        // creating and running the gui
        DigestionLog gui = new DigestionLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        // creating, configuring and running the worker
        DigestionWorker worker = new DigestionWorker(SERVERS, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Digestion, worker);
        consumer.subscribe(TOPIC_ToDigest);
        consumer.run();
    }

}
