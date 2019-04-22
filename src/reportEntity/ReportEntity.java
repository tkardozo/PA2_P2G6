package reportEntity;

import kafka.SimpleConsumer;

import static common.Properties.SERVERS;
import static common.Properties.TOPIC_TO_Report;

public class ReportEntity {
    public static void main(String[] args) {
        ReportWorker worker = new ReportWorker();
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, "report", worker);
        consumer.subscribe(TOPIC_TO_Report);
        consumer.run();
    }

}
