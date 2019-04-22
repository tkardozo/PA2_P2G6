package collectEntity;

import common.Entry;
import kafka.SimpleProducer;
import java.io.*;

import static kafka.SimpleProperties.ACK_SYNC;
import static common.Properties.SERVERS;
import static common.Properties.TOPIC_TO_Digest;

public class CollectEntity {

    public static void main(String[] args) throws IOException {
        SimpleProducer<String> producer = new SimpleProducer<>(SERVERS, ACK_SYNC);

        BufferedReader br = new BufferedReader(new FileReader(new File("src/data/source.txt")));
        String value;
        while ((value = br.readLine()) != null){
            String key = new Entry(value).getType();
            producer.send(TOPIC_TO_Digest, key, value);
            System.out.println("Sent: " + value);
        }

        br.close();
        producer.close();
    }
}
