package alarmEntity;

import kafka.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlarmWorker implements Callback<String> {

    @Override
    public void onSuccess(String key, String value) {
        List<String> msg = new ArrayList<>( Arrays.asList( value.split("[|]") ) );

        Integer speed = Integer.parseInt(msg.get(4));
        Integer maxSpeed = Integer.parseInt(msg.get(6));

        if(speed > maxSpeed){
            msg.add("ON");
        }else{
            msg.add("OFF");
        }

        StringBuilder result = new StringBuilder();
        msg.forEach(part->{
            if(result.length() > 0)
                result.append("|");
            result.append(part);
        });
        System.out.println(result.toString());
    }
}
