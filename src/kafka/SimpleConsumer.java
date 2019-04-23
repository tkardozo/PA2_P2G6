package kafka;

import digestionEntity.DigestionWorker;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.regex.Pattern;

import static kafka.SimpleProperties.*;

/**
 * Simplified consumer for KAFKA consumer, with callback onReceive
 *
 * @param <T>
 * @see Callback
 * @author P2G6
 */
public class SimpleConsumer<T> implements Runnable {

    private Consumer<String, T> consumer;
    private Callback<T> callback;
    private boolean closed = false;

    /**
     * Registers the desired callback and creates the inner consumer
     *
     * @param servers The KAFKA server endpoints
     * @param groupId The KAFKA consumer group ID
     * @param callback The desired callback
     * @param deserializer
     */
    public SimpleConsumer(String servers, String groupId, Callback<T> callback, String deserializer) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", groupId);
        props.put("key.deserializer", DESERIALIZER_STRING);
        props.put("value.deserializer", deserializer);

        this.consumer = new KafkaConsumer<>(props);
        this.callback = callback;
    }

    /**
     * Registers the desired callback and creates the default inner consumer
     *
     * @param servers The KAFKA server endpoints
     * @param groupId The KAFKA consumer group ID
     * @param callback The desired callback
     */
    public SimpleConsumer(String servers, String groupId, Callback<T> callback) {
        this(servers, groupId, callback, DESERIALIZER_STRING);
    }

    /**
     * Subscriber for topics
     *
     * @param topicName
     */
    public void subscribe(String topicName) {
        consumer.subscribe(Pattern.compile(topicName));
    }

    /**
     * The main execution of the consumer
     */
    @Override
    public void run() {
        while (!closed) {
            ConsumerRecords<String, T> records = consumer.poll(100);
            for (ConsumerRecord<String, T> record : records) {
                this.callback.onReceive(record.key(), record.value());
            }
        }
        this.consumer.close();

    }

    /**
     * Closing the receiver
     */
    public void close() {
        this.closed = true;
    }

}
