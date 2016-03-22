package main;

import net.tool.connectionSolver.ConnectionMessageImpl;
import service.net.PackageSolver;

import java.io.IOException;

/**
 * Created by xlo on 16/3/22.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws IOException {
        net.server.Client client = net.server.Client.getNewClient("client");
        net.server.Client.getInstance("client", 2);
        client.connect("127.0.0.1", 8080, new PackageSolver(new ConnectionMessageImpl()));
    }

}
