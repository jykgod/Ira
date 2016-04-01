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
    private UIPage nowPage;
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
        if (nowPage != null) {
            this.jFrame.remove(nowPage.getPanel());
            this.nowPage.destroy();
        }
        this.nowPage = uiPage;
        this.nowPage.create();
        this.jFrame.add(uiPage.getPanel());
        this.jFrame.pack();
    }

    public synchronized void lockNowPage() {
        if (nowPage != null) {
            this.nowPage.lock();
        }
    }

    public synchronized void unlockNowPage() {
        if (nowPage != null) {
            this.nowPage.unlock();
        }
    }

    public UIPage getNowPage() {
        return nowPage;
    }
}
