package kafka;

/**
 * Interface for the SimpleConsumer callback
 *
 * @see SimpleConsumer
 * @author P2G6
 * @param <T> Message type
 */
public interface Callback<T> {

    /**
     * Receives each message of the specified topics
     *
     * @param key The message type
     * @param value The full message
     */
    void onReceive(String key, T value);
}
