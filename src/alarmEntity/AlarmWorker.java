package alarmEntity;

import kafka.Callback;

public class AlarmWorker implements Callback<String> {

    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);
    }
}
