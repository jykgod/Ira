package view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlo on 16/3/30.
 * it's the main page
 */
public class PageManager {

    private static PageManager pageManager = new PageManager();

    public static PageManager getPageManager() {
        return pageManager;
    }

    private JFrame jFrame;
    private UIPage CurrentPage;
    private Map<String, UIPage> uiPageMap;

    private PageManager() {
        this.jFrame = new JFrame("ira");
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.uiPageMap = new HashMap<>();
    }

    public void showFrame(int width, int height) {
        this.jFrame.pack();
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setSize(width, height);
        this.jFrame.setVisible(true);
    }

    public synchronized void addPage(String pageName, UIPage uiPage) {
        this.uiPageMap.put(pageName, uiPage);
    }

    public synchronized void showPage(String pageName) {
        UIPage uiPage = this.uiPageMap.get(pageName);
        if (CurrentPage != null) {
            this.jFrame.remove(CurrentPage.getPanel());
            this.CurrentPage.destroy();
        }
        this.CurrentPage = uiPage;
        this.CurrentPage.create();
        this.jFrame.add(uiPage.getPanel());
        this.jFrame.pack();
    }

    public synchronized void setCurrentPageEnable(boolean enable) {
        if (CurrentPage != null) {
            this.CurrentPage.setEnable(enable);
        }
    }

    public UIPage getCurrentPage() {
        return CurrentPage;
    }
}
