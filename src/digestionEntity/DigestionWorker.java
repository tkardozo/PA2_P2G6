package digestionEntity;

import kafka.Callback;
import kafka.SimpleProducer;
import kafka.SimpleProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kafka.common.TOPIC_TO_Batch;
import static kafka.common.TOPIC_TO_Alarm;
import static kafka.common.TOPIC_TO_Report;

public class DigestionWorker implements Callback<String> {
    private SimpleProducer<String> fafProducer;
    private SimpleProducer<String> syncProducer;

    public DigestionWorker(String servers){
        this.fafProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_FAF);
        this.syncProducer = new SimpleProducer<>(servers, SimpleProperties.ACK_SYNC);
    }

    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);
        List<String> msg = new ArrayList<>( Arrays.asList( value.split("[|]") ) );

        if(key.equals("01"))
            msg.add("100");
        msg.add(2, "P-"+msg.get(0));

        StringBuilder result = new StringBuilder();
        msg.forEach(part->{
            if(result.length() > 0)
                result.append("|");
            result.append(part);
        });

        fafProducer.send(TOPIC_TO_Batch, key, result.toString());
        System.out.println("\tSent (via FAF): " + result.toString());

        syncProducer.send(TOPIC_TO_Report, key, result.toString());
        System.out.println("\tSent (via SYNC): " + result.toString());

        if(key.equals("01")) {
            syncProducer.send(TOPIC_TO_Alarm, key, result.toString());
            System.out.println("\tSent (via SYNC): " + result.toString());
        }
    }
}
