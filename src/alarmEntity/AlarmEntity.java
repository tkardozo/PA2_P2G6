package alarmEntity;

import kafka.SimpleConsumer;

import static kafka.common.SERVERS;
import static kafka.common.TOPIC_TO_Alarm_Report;

public class AlarmEntity {
    public static void main(String[] args) {
        AlarmWorker worker = new AlarmWorker();
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, worker);
        consumer.subscribe(TOPIC_TO_Alarm_Report);
        consumer.run();
    }

}
