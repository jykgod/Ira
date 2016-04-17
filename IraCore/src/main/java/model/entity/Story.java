package model.entity;

import model.JsonAble;
import model.db.DBTable;
import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONObject;

/**
 * Created by xlo on 16/3/21.
 * it's the story
 */
public class Story extends WatcherEntity implements JsonAble {

    public Story() {
    }

    public Story(DBTable.DBData data) {
        super(data);
    }

    public Story(String id, String title, String epicId, StoryType storyType, String reporter, String assignee, Priority priority, int point) {
        this.setId(id);
        this.setTitle(title);
        this.setEpicId(epicId);
        this.setStoryType(storyType);
        this.setReporter(reporter);
        this.setAssignee(assignee);
        this.setPriority(priority);
        this.setPoint(point);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("title", this.getTitle());
        jsonObject.put("epicId", this.getEpicId());
        jsonObject.put("storyType", this.getStoryType().toString());
        jsonObject.put("reporter", this.getReporter());
        jsonObject.put("assignee", this.getAssignee());
        jsonObject.put("priority", this.getPriority().toString());
        jsonObject.put("point", this.getPoint());
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
        this.setTitle(jsonObject.getString("title"));
        this.setEpicId(jsonObject.getString("epicId"));
        this.setStoryType(StoryType.valueOf(jsonObject.getString("storyType")));
        this.setReporter(jsonObject.getString("reporter"));
        this.setAssignee(jsonObject.getString("assignee"));
        this.setPriority(Priority.valueOf(jsonObject.getString("priority")));
        this.setPoint(Integer.parseInt(jsonObject.getString("point")));
    }

    public String getId() {
        return this.objectMap.get("_id").toString();
    }

    public void setId(String id) {
        this.objectMap.put("_id", id);
    }

    public String getTitle() {
        return this.objectMap.get("title").toString();
    }

    public void setTitle(String title) {
        this.objectMap.put("title", title);
    }

    public String getEpicId() {
        return this.objectMap.get("epicId").toString();
    }

    public void setEpicId(String epicId) {
        this.objectMap.put("epicId", epicId);
    }

    public StoryType getStoryType() {
        return StoryType.valueOf(this.objectMap.get("storyType").toString());
    }

    public void setStoryType(StoryType storyType) {
        this.objectMap.put("storyType", storyType);
    }

    public String getReporter() {
        return this.objectMap.get("reporter").toString();
    }

    public void setReporter(String reporter) {
        this.objectMap.put("reporter", reporter);
    }

    public String getAssignee() {
        return this.objectMap.get("assignee").toString();
    }

    public void setAssignee(String assignee) {
        this.objectMap.put("assignee", assignee);
    }

    public Priority getPriority() {
        return Priority.valueOf(this.objectMap.get("priority").toString());
    }

    public void setPriority(Priority priority) {
        this.objectMap.put("priority", priority);
    }

    public int getPoint() {
        return Integer.parseInt(this.objectMap.get("point").toString());
    }

    public void setPoint(int point) {
        this.objectMap.put("point", point);
    }
}
