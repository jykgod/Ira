package model;

import model.db.DBTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 15-12-20.
 * it's the entity
 */
public abstract class Entity {

    protected Map<String, Object> objectMap;

    public Entity() {
        this.objectMap = new HashMap<>();
    }

    public Entity(DBTable.DBData data) {
        this.objectMap = data.object;
    }

    public Map<String, Object> getObject() {
        return objectMap;
    }

}

