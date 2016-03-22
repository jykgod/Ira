package model;

import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 16/3/21.
 * it's the project
 */
public class Project implements JsonAble {

    private String id;
    private String projectName;
    private List<String> iterations;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
        this.iterations = new ArrayList<>();
    }

    public Project(String projectName, List<String> iterations) {
        this.projectName = projectName;
        this.iterations = iterations;
    }

    @Override
    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("projectName", this.getProjectName());
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(this.iterations);
        jsonObject.put("iterations", jsonArray);
        return jsonObject.toString();
    }

    @Override
    public void updateValueFromJson(String jsonString) throws JsonClassNotMatchException {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        if (!jsonObject.getString("clazz").equals(this.getClass().getName())) {
            throw new JsonClassNotMatchException();
        }

        this.setId(jsonObject.getString("id"));
        this.setProjectName(jsonObject.getString("projectName"));
        JSONArray jsonArray = jsonObject.getJSONArray("iterations");
        this.iterations.clear();
        for (Object now : jsonArray) {
            this.addIteration(now.toString());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIterations() {
        return new ArrayList<>(iterations);
    }

    public void addIteration(String iteration) {
        this.iterations.add(iteration);
    }

    public void removeIteration(String iteration) {
        this.iterations.remove(iteration);
    }

    public String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
