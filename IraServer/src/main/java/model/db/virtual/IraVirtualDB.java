package model.db.virtual;

import model.db.VirtualDB;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 2015/9/1.
 * it's the virtual db
 */
public class IraVirtualDB implements VirtualDB {

    private Map<String, IraVirtualTable> collectionMap = new HashMap<>();

    @Override
    public synchronized IraVirtualTable getTable(String collectionName) {
        if (!this.collectionMap.containsKey(collectionName)) {
            this.collectionMap.put(collectionName, new IraVirtualTable());
        }
        return this.collectionMap.get(collectionName);
    }
}
