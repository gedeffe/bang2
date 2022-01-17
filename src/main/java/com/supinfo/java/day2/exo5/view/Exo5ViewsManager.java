package com.supinfo.java.day2.exo5.view;

import com.supinfo.java.day2.exo5.control.TextServices;
import com.supinfo.java.day2.exo5.model.TextModel;

import javax.swing.*;

/**
 * To initialize independents views and connect them to control and model layer.
 */
public class Exo5ViewsManager {

    public void showFrames(final TextModel textModel, final TextServices textServices) {
        //Create and set up windows.
        final JFrame frame = new CommandsAreaWindow(textServices);
        final JWindow window = new DisplayAreaWindow(textModel);

        //Display both windows.
        frame.setVisible(true);
        window.setVisible(true);
    }
}
