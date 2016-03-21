package model;

import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONObject;

/**
 * Created by xlo on 16/3/21.
 * it's the story
 */
public class Story implements JsonAble {

    private String id;
    private String title;
    private String iterationId;
    private String epicId;
    private StoryType storyType;
    private String reporter;
    private String assignee;
    private Priority priority;
    private int point;

    public Story() {
    }

    public Story(String id, String title, String iterationId, String epicId, StoryType storyType, String reporter, String assignee, Priority priority, int point) {
        this.id = id;
        this.title = title;
        this.iterationId = iterationId;
        this.epicId = epicId;
        this.storyType = storyType;
        this.reporter = reporter;
        this.assignee = assignee;
        this.priority = priority;
        this.point = point;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("title", this.getTitle());
        jsonObject.put("iterationId", this.getIterationId());
        jsonObject.put("epicId", this.getEpicId());
        jsonObject.put("storyType", this.getStoryType().toString());
        jsonObject.put("reporter", this.getReporter());
        jsonObject.put("assignee", this.getAssignee());
        jsonObject.put("priority", this.getPriority().toString());
        jsonObject.put("point", this.getPoint());
        return jsonObject.toString();
    }

    @Override
    public void updateValueFromJson(String jsonString) throws JsonClassNotMatchException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        if (!jsonObject.getString("clazz").equals(this.getClass().getName())) {
            throw new JsonClassNotMatchException();
        }

        this.setId(jsonObject.getString("id"));
        this.setTitle(jsonObject.getString("title"));
        this.setIterationId(jsonObject.getString("iterationId"));
        this.setEpicId(jsonObject.getString("epicId"));
        this.setStoryType(StoryType.valueOf(jsonObject.getString("storyType")));
        this.setReporter(jsonObject.getString("reporter"));
        this.setAssignee(jsonObject.getString("assignee"));
        this.setPriority(Priority.valueOf(jsonObject.getString("priority")));
        this.setPoint(Integer.parseInt(jsonObject.getString("point")));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIterationId() {
        return iterationId;
    }

    public void setIterationId(String iterationId) {
        this.iterationId = iterationId;
    }

    public String getEpicId() {
        return epicId;
    }

    public void setEpicId(String epicId) {
        this.epicId = epicId;
    }

    public StoryType getStoryType() {
        return storyType;
    }

    public void setStoryType(StoryType storyType) {
        this.storyType = storyType;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
