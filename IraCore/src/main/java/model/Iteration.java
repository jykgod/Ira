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

    private Date from, to;
    private List<String> stories;

    public Iteration() {
        this.stories = new ArrayList<>();
    }

    public Iteration(Date from, Date to, List<String> stories) {
        this.from = from;
        this.to = to;
        this.stories = stories;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clazz", this.getClass().getName());
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

        this.setFrom(new Date(Long.parseLong(jsonObject.getString("from"))));
        this.setTo(new Date(Long.parseLong(jsonObject.getString("to"))));
        JSONArray jsonArray = jsonObject.getJSONArray("stories");
        this.stories.clear();
        for (Object now : jsonArray) {
            this.addStory(now.toString());
        }
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
