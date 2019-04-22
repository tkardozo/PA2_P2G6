package reportEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class ReportEntity {
    public static void main(String[] args) {
        ReportWorker worker = new ReportWorker(REPORT_Database, REPORT_Collection);
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Report, worker);
        consumer.subscribe(TOPIC_FromDigest);
        consumer.run();
    }
}
