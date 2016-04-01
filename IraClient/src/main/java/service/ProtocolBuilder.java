package service;

import model.data.ConnectionData;
import net.sf.json.JSONObject;
import service.encrypt.PasswordEncoder;

/**
 * Created by xlo on 16/2/23.
 * it's the protocol builder
 */
public final class ProtocolBuilder {

    public static byte[] getSessionId() {
        return "/getSessionID#{}".getBytes();
    }

    public static byte[] login(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", PasswordEncoder.encode(password + ConnectionData.getConnectionData().getSessionID()));
        String body = jsonObject.toString();
        return ("/login#" + body).getBytes();
    }

    public static byte[] useApp(String appName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appName", appName);
        String body = jsonObject.toString();
        return ("/useApp#" + body).getBytes();
    }

    public static byte[] testCommand() {
        return "/testCommand#{}".getBytes();
    }

}
