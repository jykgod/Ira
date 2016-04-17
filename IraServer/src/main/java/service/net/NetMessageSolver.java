package service.net;

import service.clojure.ScriptManager;

import java.net.Socket;

/**
 * Created by xlo on 16-1-1.
 * it's the abstract net message solver
 */
public class NetMessageSolver {

    public SendEvent solveMessage(byte[] body, Socket socket) throws Exception {
        byte[] url, message;
        for (int i = 0; i < body.length; i++) {
            if (body[i] == '#') {
                url = new byte[i];
                System.arraycopy(body, 0, url, 0, i);
                message = new byte[body.length - i - 1];
                System.arraycopy(body, i + 1, message, 0, body.length - i - 1);
                return sendEvent(new String(url), message, socket);
            }
        }
        return null;
    }

    private SendEvent sendEvent(String command, byte[] message, Socket socket) throws Exception {
        SendEvent event = getEvent(socket);
        event.setEventAction(() -> ScriptManager.getScriptManager()
                .invoke(command, message, socket, getEventMessageHelper(event)));
        event.submit();
        return event;
    }

    protected SendEvent getEvent(Socket socket) {
        return new SendEvent(socket);
    }

    private EventMessageHelper getEventMessageHelper(SendEvent event) {
        return new EventMessageHelper(event);
    }

}
