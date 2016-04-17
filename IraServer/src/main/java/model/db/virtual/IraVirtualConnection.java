package model.db.virtual;

import model.config.ConfigManager;
import model.config.DBConfig;
import model.db.VirtualDB;
import model.db.VirtualDBConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 2015/9/14.
 * it's the virtual connection
 */
public class IraVirtualConnection implements VirtualDBConnection {
    private static DBConfig dbConfig = (DBConfig) ConfigManager.getConfigManager().getConfig(DBConfig.class);

    private static Map<String, VirtualDB> dbMap = new HashMap<>();

    public synchronized VirtualDB getDatabase(String name) {
        if (dbConfig.getDBType(name).equals("default")) {
            if (!dbMap.containsKey(name)) {
                dbMap.put(name, new IraVirtualDB());
            }
        } else {
            if (!dbMap.containsKey(name)) {
                dbMap.put(name, new IraOldVirtualDB());
            }
        }
        return dbMap.get(name);
    }

    @Override
    public void close() throws IOException {

    }
}
