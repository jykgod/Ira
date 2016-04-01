package view;

import javax.swing.*;

/**
 * Created by xlo on 16/3/30.
 * it's the ui page interface
 */
public interface UIPage {

    void lock();

    void unlock();

    void create();

    void destroy();

    void sendMessage();

    JPanel getPanel();

}
