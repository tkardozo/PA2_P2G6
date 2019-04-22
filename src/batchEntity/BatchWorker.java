package batchEntity;

import kafka.Callback;

public class BatchWorker implements Callback<String> {

    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);
    }
}
