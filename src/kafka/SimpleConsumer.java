package kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.regex.Pattern;

import static kafka.SimpleProperties.*;

public class SimpleConsumer <T> implements Runnable{
    private Consumer<String, T> consumer;
    private Callback<T> callback;
    private boolean closed = false;

    public SimpleConsumer(String servers, String groupId, Callback<T> callback, String deserializer) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", groupId);
        props.put("key.deserializer", DESERIALIZER_STRING);
        props.put("value.deserializer", deserializer);

        this.consumer = new KafkaConsumer<>(props);
        this.callback = callback;
    }

    public SimpleConsumer(String servers, String groupId, Callback<T> callback){
        this(servers, groupId, callback, DESERIALIZER_STRING);
    }

    public void subscribe (String topicName){
        consumer.subscribe(Pattern.compile(topicName));
    }

    @Override
    public void run() {
        while(!closed){
            ConsumerRecords<String, T> records = consumer.poll(100);
            for( ConsumerRecord<String, T> record : records){
                this.callback.onSuccess(record.key(), record.value());
            }
        }
        this.consumer.close();

    }

    public void close(){
        this.closed = true;
    }

}
