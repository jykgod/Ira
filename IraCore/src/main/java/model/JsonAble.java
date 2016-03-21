package model;

import model.exception.JsonClassNotMatchException;

/**
 * Created by xlo on 16/3/21.
 * it's the interface for class who can be serialized to json
 */
public interface JsonAble {

    String toJsonString();

    void updateValueFromJson(String jsonString) throws JsonClassNotMatchException;

}
