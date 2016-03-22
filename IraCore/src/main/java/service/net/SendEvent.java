package service.net;

import javafx.util.Pair;
import model.db.DBClient;
import model.db.event.Event;
import model.log.LogManager;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 15-11-2.
 * it's the event
 */
public class SendEvent extends Event {
    protected Socket socket;
    protected List<Pair<String, byte[]>> successMessage, failedMessage, commitMessage;
    protected EventAction eventAction;

    public SendEvent(Socket socket) {
        this.socket = socket;
        this.successMessage = new ArrayList<>();
        this.failedMessage = new ArrayList<>();
        this.commitMessage = new ArrayList<>();
    }

    //TODO add lock
//    public void lockByClass(List<Class<? extends DBClient>> name) {
//        String[] value = new String[name.size()];
//        for (int i = 0; i < name.size(); i++) {
//            value[i] = WalletDBConfig.getConfig().getDBLockName(name.get(i));
//        }
//        lock(value);
//    }

    public void setEventAction(EventAction eventAction) {
        this.eventAction = eventAction;
    }

    public void sendWhileCommit(String url, byte[] message) {
        this.commitMessage.add(new Pair<>(url, message));
    }

    public void sendWhileSuccess(String url, byte[] message) {
        this.successMessage.add(new Pair<>(url, message));
    }

    public void sendWhileFailed(String url, byte[] message) {
        this.failedMessage.add(new Pair<>(url, message));
    }

    @Override
    protected void whenCommit() {
        super.whenCommit();
        sendMessage(this.commitMessage);
    }

    @Override
    protected void whenFailed() {
        super.whenFailed();
        sendMessage(this.failedMessage);
        LogManager.getLogManager().writeLog("warn", "fail event", SessionManager.getSessionManager().getUsername(socket));
    }

    @Override
    protected void whenSucceed() {
        super.whenSucceed();
        sendMessage(this.successMessage);
    }

    @Override
    public boolean run() throws Exception {
        return eventAction.action();
    }

    protected void sendMessage(List<Pair<String, byte[]>> message) {
        PackageSolver packageSolver = SessionManager.getSessionManager().getPackageServer(this.socket);
        for (Pair<String, byte[]> now : message) {
            packageSolver.addMessage(now.getKey(), now.getValue());
        }
    }

    @FunctionalInterface
    public interface EventAction {
        boolean action() throws Exception;
    }
}
