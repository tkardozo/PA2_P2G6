package alarmEntity;

import kafka.SimpleConsumer;

import static common.Properties.SERVERS;
import static common.Properties.TOPIC_TO_Alarm;

public class AlarmEntity {
    public static void main(String[] args) {
        AlarmWorker worker = new AlarmWorker();
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, "alarm", worker);
        consumer.subscribe(TOPIC_TO_Alarm);
        consumer.run();
    }

}
