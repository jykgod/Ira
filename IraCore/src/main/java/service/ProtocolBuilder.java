package service;

import model.entity.Priority;
import model.entity.Story;
import model.entity.StoryType;
import net.sf.json.JSONObject;
import service.encrypt.PasswordEncoder;

/**
 * Created by xlo on 16/2/23.
 * it's the protocol builder
 */
public class ProtocolBuilder {

    public static byte[] getSessionId() {
        return "/getSessionID#{}".getBytes();
    }

    public static byte[] login(String username, String password, String sessionID) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", PasswordEncoder.encode(password + sessionID));
        String body = jsonObject.toString();
        return ("/login#" + body).getBytes();
    }

    public static byte[] useApp(String appName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName", appName);
        String body = jsonObject.toString();
        return ("/useApp#" + body).getBytes();
    }

    public static byte[] addStory(String id, String title, String epicId, StoryType storyType, String reporter, String assignee, Priority priority, int point) {
        String body = new Story(id, title, epicId, storyType, reporter, assignee, priority, point).toJsonString();
        return ("addStory#" + body).getBytes();
    }

    public static byte[] addWatcherToStory(String id, String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("username", username);
        String body = jsonObject.toString();
        return ("addWatcherToStory#" + body).getBytes();
    }

    public static byte[] testCommand() {
        return "/testCommand#{}".getBytes();
    }

}
