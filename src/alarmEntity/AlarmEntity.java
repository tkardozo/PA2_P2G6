package alarmEntity;

import kafka.SimpleConsumer;

import static common.Properties.*;

public class AlarmEntity {
    public static void main(String[] args) {
        AlarmWorker worker = new AlarmWorker();
        SimpleConsumer<String> consumer = new SimpleConsumer<>(SERVERS, GROUP_ID_Alarm, worker);
        consumer.subscribe(TOPIC_FromDigest_ALARM);
        consumer.run();
    }
}
