package controller;

import model.config.ConfigManager;
import model.db.NeedClearDBTesting;
import model.entity.Priority;
import model.entity.StoryType;
import net.server.Client;
import org.junit.After;
import org.junit.Before;
import service.ProtocolBuilder;
import service.net.SessionManager;

import java.net.Socket;

/**
 * Created by xlo on 16/4/14.
 * it's the logic testing
 */
public abstract class LogicTesting implements NeedClearDBTesting {

    protected Socket socket;

    @Before
    public void setUp() throws Exception {
        ConfigManager.getConfigManager();
        this.socket = new Socket();
        SessionManager.getSessionManager().registerSession(this.socket);
    }

    @After
    public void tearDown() throws Exception {
        clearDB();
    }

    protected void logout() {
        SessionManager.getSessionManager().registerSession(this.socket);
    }

    protected NoSendEvent loginApp(String username) throws Exception {
        NoSendNetMessageSolver noSendNetMessageSolver = new NoSendNetMessageSolver();
        return (NoSendEvent) noSendNetMessageSolver
                .solveMessage(TestingProtocolBuilder.loginApp(username), socket);
    }

    protected NoSendEvent addStory(String id, String title, String epicId, StoryType storyType, String reporter, String assignee, Priority priority, int point) throws Exception {
        NoSendNetMessageSolver noSendNetMessageSolver = new NoSendNetMessageSolver();
        return (NoSendEvent) noSendNetMessageSolver
                .solveMessage(TestingProtocolBuilder.addStory(id, title, epicId, storyType, reporter, assignee, priority, point), socket);
    }

    protected NoSendEvent addWatcherToStory(String id, String username) throws Exception {
        NoSendNetMessageSolver noSendNetMessageSolver = new NoSendNetMessageSolver();
        return (NoSendEvent) noSendNetMessageSolver
                .solveMessage(TestingProtocolBuilder.addWatcherToStory(id, username), socket);
    }

}
