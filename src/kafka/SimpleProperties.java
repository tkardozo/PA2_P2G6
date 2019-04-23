package kafka;

/**
 * Helper class to change the behaviour of the KAFKA SimpleProducer and
 * SimpleConsumer, in a simple file
 *
 * @see SimpleProducer
 * @see SimpleConsumer
 * @author P2G6
 */
public class SimpleProperties {

    /**
     * String serializer for SimpleProducer
     *
     * @see SimpleProducer
     */
    public static final String SERIALIZER_STRING = "org.apache.kafka.common.serialization.StringSerializer";

    /**
     * String deserializer for SimpleConsumer
     *
     * @see SimpleConsumer
     */
    public static final String DESERIALIZER_STRING = "org.apache.kafka.common.serialization.StringDeserializer";

    /**
     * Acknowledge type for "Fire & Forget" for SimpleProducer
     *
     * @see SimpleProducer
     */
    public static final String ACK_FAF = "0";

    /**
     * Acknowledge type for "Synchronous" for SimpleProducer
     *
     * @see SimpleProducer
     */
    public static final String ACK_SYNC = "all";
}
