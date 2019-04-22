package alarmEntity;

import common.Entry;
import kafka.Callback;

public class AlarmWorker implements Callback<String> {
    private AlarmLog gui;

    public AlarmWorker(AlarmLog gui) {
        this.gui = gui;
    }
    
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

        this.gui.log(entry.toString());
    }
}
