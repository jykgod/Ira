package controller;

import javafx.util.Pair;
import service.net.SendEvent;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xlo on 16/2/23.
 * it's the no send event
 */
public class NoSendEvent extends SendEvent {

    private List<Pair<String, byte[]>> message;

    public NoSendEvent(Socket socket) {
        super(socket);
        message = new ArrayList<>();
    }

    @Override
    protected void sendMessage(List<Pair<String, byte[]>> message) {
        this.message.addAll(message);
    }

    @Override
    public void submit() {
        super.call();
    }

    public List<Pair<String, byte[]>> getMessage() {
        return message;
    }
}
