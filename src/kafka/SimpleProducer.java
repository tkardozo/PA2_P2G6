package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static kafka.SimpleProperties.SERIALIZER_STRING;

/**
 * Simplified producer for KAFKA producer
 *
 * @param <T>
 * @see Callback
 * @author P2G6
 */
public class SimpleProducer<T> {

    private Producer<String, T> producer;

    /**
     * Registers the desired callback and creates the inner producer
     *
     * @param servers The KAFKA server endpoints
     * @param serializer
     * @param ackType The desired acknowledge type for this producer
     */
    public SimpleProducer(String servers, String serializer, String ackType) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("key.serializer", SERIALIZER_STRING);
        props.put("value.serializer", serializer);
        props.put("acks", ackType);

        this.producer = new KafkaProducer<>(props);
    }

    /**
     * Registers the desired callback and creates the default inner producer
     *
     * @param servers The KAFKA server endpoints
     * @param ackType The desired acknowledge type for this producer
     */
    public SimpleProducer(String servers, String ackType) {
        this(servers, SERIALIZER_STRING, ackType);
    }

    /**
     * Send a message to the desired topic
     *
     * @param topicName
     * @param key
     * @param msg
     */
    public void send(String topicName, String key, T msg) {
        ProducerRecord<String, T> record = new ProducerRecord<>(topicName, key, msg);
        producer.send(record);
    }

    /**
     * Closing the producer
     */
    public void close() {
        this.producer.close();
    }

}
