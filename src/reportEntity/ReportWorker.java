package reportEntity;

import kafka.Callback;

public class ReportWorker implements Callback<String> {

    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);
    }
}
