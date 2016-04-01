package main;

import model.config.ConfigManager;
import view.PageManager;
import view.connection.Connection;

import java.io.IOException;

/**
 * Created by xlo on 16/3/22.
 * it's the main class
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ConfigManager.getConfigManager();

        PageManager.getPageManager().showFrame(200, 100);

        PageManager.getPageManager().addPage("connection", new Connection());
        PageManager.getPageManager().showPage("connection");
    }

}
