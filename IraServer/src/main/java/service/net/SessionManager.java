package service.net;

import model.log.LogManager;
import net.tool.connectionManager.ConnectionManager;

import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by xlo on 2015/11/4.
 * it's the session manager
 */
public class SessionManager {
    private Map<Socket, SessionMessage> sessionMessage;

    private static SessionManager sessionManager;

    private SessionManager() {
        this.sessionMessage = new HashMap<>();
    }

    public static SessionManager getSessionManager() {
        if (sessionManager == null) {
            synchronized (SessionManager.class) {
                if (sessionManager == null) {
                    sessionManager = new SessionManager();
                }
            }
        }
        return sessionManager;
    }

    public void registerSession(Socket socket) {
        this.sessionMessage.put(socket, new SessionMessage());
        SessionMessage sessionMessage = this.sessionMessage.get(socket);
        sessionMessage.setSessionID(Math.abs(new Random().nextLong()));
        sessionMessage.setPackageServer((PackageSolver) ConnectionManager.getSolverManager().getSolver(socket));
    }

    public SessionMessage getSessionMessage(Socket socket) {
        return this.sessionMessage.get(socket);
    }

    void removeSession(Socket socket) {
        this.sessionMessage.remove(socket);
    }

    public void loginSession(Socket socket, String username) {
        this.sessionMessage.get(socket).setUsername(username);
        LogManager.getLogManager().writeLog("log", "login", username);
    }

    public boolean isLogin(Socket socket) {
        return this.sessionMessage.get(socket).getUsername() != null;
    }

    public String getUsername(Socket socket) {
        return this.sessionMessage.get(socket).getUsername();
    }

    public PackageSolver getPackageServer(Socket socket) {
        return this.sessionMessage.get(socket).getPackageServer();
    }

    public void closeAllSession() throws IOException {
        for (Socket socket : sessionMessage.keySet()) {
            socket.close();
        }
        sessionMessage.clear();
    }

    public class SessionMessage {
        private long sessionID;
        private String username;
        private PackageSolver packageServer;
        private PublicKey publicKey;
        private boolean encryption;

        public void setEncryption(boolean encryption) {
            this.encryption = encryption;
        }

        public boolean isEncryption() {
            return encryption;
        }

        public void setSessionID(long sessionID) {
            this.sessionID = sessionID;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getSessionID() {
            return sessionID;
        }

        public String getUsername() {
            return username;
        }

        public void setPublicKey(PublicKey publicKey) {
            this.publicKey = publicKey;
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public void setPackageServer(PackageSolver packageServer) {
            this.packageServer = packageServer;
        }

        public PackageSolver getPackageServer() {
            return packageServer;
        }
    }
}
