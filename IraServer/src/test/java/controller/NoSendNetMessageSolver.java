package controller;

import service.net.NetMessageSolver;
import service.net.SendEvent;

import java.net.Socket;

/**
 * Created by xlo on 16/4/14.
 * it's the no send net message solver
 */
public class NoSendNetMessageSolver extends NetMessageSolver {

    @Override
    protected SendEvent getEvent(Socket socket) {
        return new NoSendEvent(socket);
    }
}
