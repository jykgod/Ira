package model.entity;

import model.JsonAble;
import model.db.DBTable;
import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 16/4/2.
 * it's the entity with watcher
 */
public class WatcherEntity extends Entity implements JsonAble {

    public WatcherEntity() {
        this.objectMap.put("watcher", new ArrayList<>());
    }

    public WatcherEntity(DBTable.DBData data) {
        super(data);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(this.getWatcher());
        jsonObject.put("watcher", jsonArray);
        return jsonObject;
    }

    @Override
    public void updateValueFromJson(String jsonString) throws JsonClassNotMatchException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        if (!jsonObject.getString("clazz").equals(this.getClass().getName())) {
            throw new JsonClassNotMatchException();
        }

        JSONArray jsonArray = jsonObject.getJSONArray("watcher");
        this.clearWatcher();
        for (Object now : jsonArray) {
            this.addWatcher(now.toString());
        }
    }

    public List<String> getWatcher() {
        ArrayList list = ((ArrayList)this.objectMap.get("watcher"));
        ArrayList<String> result = new ArrayList<>();
        for (Object now : list) {
            result.add(now.toString());
        }
        return result;
    }

    public void addWatcher(String watcher) {
        //noinspection unchecked
        ((ArrayList)this.objectMap.get("watcher")).add(watcher);
    }

    public void removeIteration(String watcher) {
        ((ArrayList)this.objectMap.get("watcher")).remove(watcher);
    }

    public void clearWatcher() {
        ((ArrayList) this.objectMap.get("watcher")).clear();
    }
}
