package service.net;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by xlo on 16/2/23.
 * it's the event message helper
 */
public class EventMessageHelper {

    private SendEvent event;

    public EventMessageHelper(SendEvent event) {
        this.event = event;
    }

    public void setDefaultMessage(String url) {
        setSuccessMessage(url);
        setFailedMessage(url);
    }

    public void setSuccessMessage(String url) {
        setSuccessMessage(url, "ok");
    }

    public void setSuccessMessage(String url, Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        event.sendWhileSuccess(url, jsonObject.toString().getBytes());
    }

    public void setSuccessMessage(String url, byte[] message) {
        event.sendWhileSuccess(url, message);
    }

    public void setSuccessMessage(String url, String message) {
        byte[] bytes = buildDefaultResult(message);
        event.sendWhileSuccess(url, bytes);
    }

    public void setSuccessMessage(String url, List<Map<String, String>> list) {
        byte[] bytes = buildArrayResult(list);
        event.sendWhileSuccess(url, bytes);
    }

    public void setFailedMessage(String url) {
        setFailedMessage(url, "fail");
    }

    public void setFailedMessage(String url, String message) {
        byte[] bytes = buildDefaultResult(message);
        event.sendWhileFailed(url, bytes);
    }

    protected byte[] buildDefaultResult(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", message);
        return jsonObject.toString().getBytes();
    }

    protected byte[] buildArrayResult(List<Map<String, String>> list) {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> now : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.putAll(now);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString().getBytes();
    }

}
