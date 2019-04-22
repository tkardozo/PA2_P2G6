package reportEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class ReportEntity {
    public static void main(String[] args) {
        ReportLog gui = new ReportLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });
        ReportWorker worker = new ReportWorker(REPORT_Database, REPORT_Collection, gui);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Report, worker);
        consumer.subscribe(TOPIC_FromDigest);
        consumer.run();
    }
}
