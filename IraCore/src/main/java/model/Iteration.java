package model;

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
public class Iteration implements JsonAble {

    private String id;
    private Date from, to;
    private List<String> stories;

    public Iteration() {
        this.stories = new ArrayList<>();
    }

    public Iteration(String id, Date from, Date to, List<String> stories) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.stories = stories;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("from", this.getFrom().getTime() + "");
        jsonObject.put("to", this.getTo().getTime() + "");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(stories);
        jsonObject.put("stories", jsonArray);
        return jsonObject.toString();
    }

    @Override
    public void updateValueFromJson(String jsonString) throws JsonClassNotMatchException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        if (!jsonObject.getString("clazz").equals(this.getClass().getName())) {
            throw new JsonClassNotMatchException();
        }

        this.setId(jsonObject.getString("id"));
        this.setFrom(new Date(Long.parseLong(jsonObject.getString("from"))));
        this.setTo(new Date(Long.parseLong(jsonObject.getString("to"))));
        JSONArray jsonArray = jsonObject.getJSONArray("stories");
        this.stories.clear();
        for (Object now : jsonArray) {
            this.addStory(now.toString());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public List<String> getStories() {
        return new ArrayList<>(this.stories);
    }

    public void addStory(String story) {
        this.stories.add(story);
    }

    public void removeStory(String story) {
        this.stories.remove(story);
    }

}
