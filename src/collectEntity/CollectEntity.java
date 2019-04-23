package collectEntity;

import common.Entry;
import kafka.SimpleProducer;
import java.io.*;

import static common.Properties.*;
import static kafka.SimpleProperties.ACK_FAF;
import static kafka.SimpleProperties.ACK_SYNC;

/**
 * The entity responsible for creating all messages and send them over KAFKA
 *
 * @author P2G6
 */
public class CollectEntity {

    /**
     * Main execution of the entity
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // creating and running the gui
        CollectLog gui = new CollectLog();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.setVisible(true);
            }
        });

        // creating the producers (one "fire and forget" and one "synchronous")
        SimpleProducer<String> fafProducer = new SimpleProducer<>(SERVERS, ACK_FAF);
        SimpleProducer<String> syncProducer = new SimpleProducer<>(SERVERS, ACK_SYNC);

        // read each line of the provided file
        BufferedReader br = new BufferedReader(new FileReader(new File(COLLECT_File)));
        String value;
        while ((value = br.readLine()) != null) {
            String key = new Entry(value).getType();
            // send message ovre KAFKA, is message is from the HEARTBEAT type, can be sent as "fire and forget"
            if (key.equals(MSG_HEARTBEAT)) {
                fafProducer.send(TOPIC_ToDigest, key, value);
            } else {
                syncProducer.send(TOPIC_ToDigest, key, value);
            }
            gui.log(value);
        }

        br.close();
        fafProducer.close();
        syncProducer.close();
    }
}
