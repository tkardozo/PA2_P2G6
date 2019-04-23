package reportEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

/**
 * The entity responsible for logging each message to a MongoDB
 *
 * @author P2G6
 */
public class ReportEntity {

    /**
     * Main execution of the entity
     *
     * @param args
     */
    public static void main(String[] args) {
        // creating and running the gui
        ReportLog gui = new ReportLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        // creating, configuring and running the worker
        ReportWorker worker = new ReportWorker(REPORT_Database, REPORT_Collection, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Report, worker);
        consumer.subscribe(TOPIC_FromDigestion);
        consumer.run();
    }
}
