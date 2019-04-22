package collectEntity;

import common.Entry;
import kafka.SimpleProducer;
import java.io.*;

import static common.Properties.*;
import static kafka.SimpleProperties.ACK_FAF;
import static kafka.SimpleProperties.ACK_SYNC;

public class CollectEntity {

    public static void main(String[] args) throws IOException {
        CollectLog gui = new CollectLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });
        
        SimpleProducer<String> fafProducer = new SimpleProducer<>(SERVERS, ACK_FAF);
        SimpleProducer<String> syncProducer = new SimpleProducer<>(SERVERS, ACK_SYNC);

        BufferedReader br = new BufferedReader(new FileReader(new File(COLLECT_File)));
        String value;
        while ((value = br.readLine()) != null){
            String key = new Entry(value).getType();
            if(key.equals(MSG_HEARTBEAT)){
                fafProducer.send(TOPIC_ToDigest, key, value);
            }else{
                syncProducer.send(TOPIC_ToDigest, key, value);
            }
            gui.log(value);
        }

        br.close();
        fafProducer.close();
        syncProducer.close();
    }
}
