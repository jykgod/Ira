package model.entity;

import model.JsonAble;
import model.db.DBTable;
import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xlo on 16/3/21.
 * it's the iteration
 */
public class Iteration extends WatcherEntity implements JsonAble {

    public Iteration(DBTable.DBData data) {
        super(data);
    }

    public Iteration() {
        this.objectMap.put("stories", new ArrayList<String>());
    }

    public Iteration(String id, Date from, Date to, ArrayList<String> stories) {
        this.setId(id);
        this.setFrom(from);
        this.setTo(to);
        this.objectMap.put("stories", stories);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("from", this.getFrom().getTime() + "");
        jsonObject.put("to", this.getTo().getTime() + "");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(this.getStories());
        jsonObject.put("stories", jsonArray);
        return jsonObject;
    }

    @Override
    public void updateValueFromJson(String jsonString) throws JsonClassNotMatchException {
        super.updateValueFromJson(jsonString);
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        if (!jsonObject.getString("clazz").equals(this.getClass().getName())) {
            throw new JsonClassNotMatchException();
        }

        this.setId(jsonObject.getString("id"));
        this.setFrom(new Date(Long.parseLong(jsonObject.getString("from"))));
        this.setTo(new Date(Long.parseLong(jsonObject.getString("to"))));
        JSONArray jsonArray = jsonObject.getJSONArray("stories");
        this.clearStories();
        for (Object now : jsonArray) {
            this.addStory(now.toString());
        }
    }

    public String getId() {
        return this.objectMap.get("_id").toString();
    }

    public void setId(String id) {
        this.objectMap.put("_id", id);
    }

    public Date getFrom() {
        return new Date(Long.parseLong(this.objectMap.get("from").toString()));
    }

    public void setFrom(Date from) {
        this.objectMap.put("from", from.getTime());
    }

    public Date getTo() {
        return new Date(Long.parseLong(this.objectMap.get("to").toString()));
    }

    public void setTo(Date to) {
        this.objectMap.put("to", to.getTime());
    }

    public List<String> getStories() {
        ArrayList list = ((ArrayList)this.objectMap.get("stories"));
        ArrayList<String> result = new ArrayList<>();
        for (Object now : list) {
            result.add(now.toString());
        }
        return result;
    }

    public void addStory(String story) {
        //noinspection unchecked
        ((ArrayList)this.objectMap.get("stories")).add(story);
    }

    public void removeStory(String story) {
        ((ArrayList)this.objectMap.get("stories")).remove(story);
    }

    private void clearStories() {
        ((ArrayList)this.objectMap.get("stories")).clear();
    }

}
