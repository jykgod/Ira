package model;

import model.exception.JsonClassNotMatchException;
import net.sf.json.JSONObject;

/**
 * Created by xlo on 16/3/21.
 * it's the interface for class who can be serialized to json
 */
public interface JsonAble {

    JSONObject toJson();

    default String toJsonString() {
        return toJson().toString();
    }

    void updateValueFromJson(String jsonString) throws JsonClassNotMatchException;

}
