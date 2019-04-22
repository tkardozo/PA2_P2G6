package reportEntity;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import kafka.Callback;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportWorker implements Callback<String> {
    private MongoCollection collection;

    public ReportWorker(String database, String collection){
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase(database);
        this.collection = db.getCollection(collection);
        this.collection.drop();
    }


    @Override
    public void onSuccess(String key, String value) {
        System.out.println("Received: " + value);

        List<String> msg = new ArrayList<>(Arrays.asList(value.split("[|]")));

        Document doc = new Document()
                .append("time", msg.get(1))
                .append("value", value);

        this.collection.insertOne(doc);
    }
}
