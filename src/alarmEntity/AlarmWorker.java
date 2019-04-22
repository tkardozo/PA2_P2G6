package alarmEntity;

import common.Entry;
import kafka.Callback;

public class AlarmWorker implements Callback<String> {

    @Override
    public void onSuccess(String key, String value) {
        Entry entry = new Entry(value);

        Integer speed = Integer.parseInt(entry.get(4));
        Integer maxSpeed = Integer.parseInt(entry.get(6));

        if(speed > maxSpeed){
            entry.add("ON");
        }else{
            entry.add("OFF");
        }

        System.out.println(entry.toString());
    }
}
