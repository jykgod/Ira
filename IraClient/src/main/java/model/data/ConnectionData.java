package model.data;

/**
 * Created by xlo on 16/4/1.
 * it's the connection data
 */
public class ConnectionData {

    private static ConnectionData connectionData = new ConnectionData();

    public static ConnectionData getConnectionData() {
        return connectionData;
    }

    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
