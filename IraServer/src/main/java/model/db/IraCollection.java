package model.db;

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
public abstract class IraCollection extends DBTable {

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

    public void insertData(Document document) {
        this.lockCollection();
        this.insert(document);
    }

    public List<DBData> getAllDataList(Document document) {
        this.lockCollection();
        List<Map<String, Object>> iterator = this.collection.find(document);
        return iterator.stream().map(this::addDocumentToUsing).collect(Collectors.toCollection(LinkedList::new));
    }

    public List<DBData> getAllDataListData(Document document) {
        this.lockCollection();
        List<Map<String, Object>> iterator = this.collection.find(document);
        this.unlockCollection();
        return iterator.stream().map(this::getDocumentNotUsing).collect(Collectors.toCollection(LinkedList::new));
    }

    public void loadFromString(String string) {
        JSONArray jsonArray = JSONArray.fromObject(string);
        for (Object object : jsonArray) {
            Document document = Document.parse(object.toString());
            this.insertData(document);
        }
    }
}
