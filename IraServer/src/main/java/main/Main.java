package main;

import model.config.ConfigManager;
import model.db.event.Event;
import net.server.Server;
import net.tool.connectionSolver.ConnectionMessageImpl;
import service.net.PackageSolver;

import java.util.concurrent.Executors;

/**
 * Created by xlo on 16/3/22.
 * it's the main class
 */
public class Main {
    public static void main(String[] args) {
        int port = 8080;
        int threadNum = 2;

        ConfigManager.getConfigManager();

        Server server = Server.getNewServer("server", () -> new PackageSolver(new ConnectionMessageImpl()));
        Event.executorService = Executors.newFixedThreadPool(threadNum);
        server.getInstance(port, threadNum);
        server.accept();
    }
}
