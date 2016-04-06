package model.db.mongo;

import com.mongodb.MongoClient;
import model.config.ConfigManager;
import model.config.DBConfig;
import model.db.VirtualDB;
import model.db.VirtualDBConnection;

import java.io.IOException;

/**
 * Created by xlo on 2015/9/14.
 * it's the connection of mongoDB
 */
public class IraMongoConnection implements VirtualDBConnection {
    private static DBConfig dbConfig = (DBConfig) ConfigManager.getConfigManager().getConfig(DBConfig.class);

    private static MongoClient mongoClient = null;

    @SuppressWarnings("deprecation")
    @Override
    public VirtualDB getDatabase(String name) {
        if (mongoClient == null) {
            synchronized (IraMongoConnection.class) {
                if (mongoClient == null) {
                    mongoClient = new MongoClient(dbConfig.getHost(), dbConfig.getPort());
                }
            }
        }
        if (dbConfig.getDBType(name).equals("default")) {
            return new IraMongoDB(mongoClient.getDatabase(name));
        } else {
            return new IraOldMongoDB(mongoClient.getDB(name));
        }
    }

    @Override
    public void close() throws IOException {
        mongoClient.close();
    }
}
