package model;

import model.db.DBTable;
import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 16/3/21.
 * it's the project
 */
public class Project extends WatcherEntity implements JsonAble {

    public Project() {
        this.objectMap.put("iterations", new ArrayList<String>());
    }

    public Project(DBTable.DBData data) {
        super(data);
    }

    public Project(String projectName, ArrayList<String> iterations) {
        this.setProjectName(projectName);
        this.objectMap.put("iterations", iterations);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("clazz", this.getClass().getName());
        jsonObject.put("id", this.getId());
        jsonObject.put("projectName", this.getProjectName());
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(this.getIterations());
        jsonObject.put("iterations", jsonArray);
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
        this.setProjectName(jsonObject.getString("projectName"));
        JSONArray jsonArray = jsonObject.getJSONArray("iterations");
        this.clearIterations();
        for (Object now : jsonArray) {
            this.addIteration(now.toString());
        }
    }

    public String getId() {
        return this.objectMap.get("_id").toString();
    }

    public void setId(String id) {
        this.objectMap.put("_id", id);
    }

    public List<String> getIterations() {
        ArrayList list = ((ArrayList)this.objectMap.get("iterations"));
        ArrayList<String> result = new ArrayList<>();
        for (Object now : list) {
            result.add(now.toString());
        }
        return result;
    }

    public void addIteration(String iteration) {
        //noinspection unchecked
        ((ArrayList)this.objectMap.get("iterations")).add(iteration);
    }

    public void removeIteration(String iteration) {
        ((ArrayList)this.objectMap.get("iterations")).remove(iteration);
    }

    public String getProjectName() {
        return this.objectMap.get("projectName").toString();
    }

    private void setProjectName(String projectName) {
        this.objectMap.put("projectName", projectName);
    }

    private void clearIterations() {
        ((ArrayList)this.objectMap.get("iterations")).clear();
    }
}
