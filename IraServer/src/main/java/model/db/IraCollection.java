package model.db;

import model.Entity;
import model.Iteration;
import net.sf.json.JSONArray;
import org.bson.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xlo on 15-11-1.
 * it's the wallet collection
 */
public abstract class IraCollection<T extends Entity> extends DBTable {

    public static void closeConnect() throws IOException {
        virtualDBConnection.close();
    }

    @Override
    protected void updateUsing() {
        using.values().stream().filter(now -> !now.object.equals(now.past))
                .forEach(now -> collection.updateOne(new Document("_id", now.id), new Document("$set", now.object)));
    }

    @Override
    protected Map<String, Object> buildNewDocument() {
        return new Document();
    }

    @Override
    protected String getIdObjectKey() {
        return "_id";
    }

    public void clearDB() {
        this.collection.find().forEach(this.collection::deleteOne);
    }

    private void insertData(Document document) {
        this.lockCollection();
        this.insert(document);
    }
}
