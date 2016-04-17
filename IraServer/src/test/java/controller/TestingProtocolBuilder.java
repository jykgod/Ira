package controller;

import net.sf.json.JSONObject;
import service.ProtocolBuilder;

/**
 * Created by xlo on 16/4/14.
 * it's the protocol builder for testing
 */
public class TestingProtocolBuilder extends ProtocolBuilder {

    public static byte[] loginApp(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        String body = jsonObject.toString();
        return ("/loginApp#" + body).getBytes();
    }

}
