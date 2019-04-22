package kafka;

public interface Callback<T> {
    void onSuccess(String key, T value);
}
