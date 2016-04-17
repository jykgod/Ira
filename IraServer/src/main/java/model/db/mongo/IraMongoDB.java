package model.db.mongo;

import com.mongodb.client.MongoDatabase;
import model.db.VirtualDB;
import model.db.VirtualTable;

/**
 * Created by xlo on 2015/9/14.
 * it's the db of mongo
 */
public class IraMongoDB implements VirtualDB {
    MongoDatabase mongoDatabase;

    IraMongoDB(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public VirtualTable getTable(String name) {
        return new IraMongoTable(mongoDatabase.getCollection(name));
    }
}
