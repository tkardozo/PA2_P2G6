package collectEntity;

import kafka.SimpleProducer;
import java.io.*;
import java.util.Objects;

import static kafka.SimpleProperties.ACK_SYNC;
import static kafka.common.SERVERS;
import static kafka.common.TOPIC_TO_Digest;

public class CollectEntity {

    public static void main(String[] args) throws IOException {
        SimpleProducer<String> producer = new SimpleProducer<>(SERVERS, ACK_SYNC);

        File file = new CollectEntity().getSource();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String value;
        while ((value = br.readLine()) != null) {
            String key = value.split("[|]")[2];
            producer.send(TOPIC_TO_Digest, key, value);
            System.out.println("Sent: " + value);
        }

        br.close();
        producer.close();
    }

    private File getSource(){
        return new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("data/source.txt")).getFile()
        );
    }
}
