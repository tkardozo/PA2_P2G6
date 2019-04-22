package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static kafka.SimpleProperties.SERIALIZER_STRING;

public class SimpleProducer <T>{
    private Producer<String, T> producer;

    public SimpleProducer(String servers, String serializer, String ackType) {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("key.serializer", SERIALIZER_STRING);
        props.put("value.serializer", serializer);
        props.put("acks", ackType);

        this.producer = new KafkaProducer<>(props);
    }

    public SimpleProducer(String servers, String ackType){
        this(servers, SERIALIZER_STRING, ackType);
    }

    public void send(String topicName, String key, T msg){
        ProducerRecord<String, T> record = new ProducerRecord<>(topicName, key, msg);
        producer.send(record);
    }

    public void close(){
        this.producer.close();
    }

}
