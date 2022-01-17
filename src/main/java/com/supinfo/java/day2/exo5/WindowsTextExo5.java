package com.supinfo.java.day2.exo5;


import com.supinfo.java.day2.exo5.control.TextServices;
import com.supinfo.java.day2.exo5.control.TextServicesImpl;
import com.supinfo.java.day2.exo5.model.TextModel;
import com.supinfo.java.day2.exo5.model.TextModelImpl;
import com.supinfo.java.day2.exo5.view.Exo5ViewsManager;

import javax.swing.*;

/**
 * Exercise 5 :
 * <p>
 * We want to create a Swing program that has a main window where we type text, and a secondary window where the text is sent, line by line.
 * The main window will contain an editable text area where the user will type the line to send, as well as a send button.
 * It will also close the program.
 * The secondary window will not have a menu bar. It will contain a non-editable text area where the received text will be written
 */
public class WindowsTextExo5 {

    public static void main(final String[] args) {
        final TextModel textModel = new TextModelImpl();
        final TextServices textServices = new TextServicesImpl(textModel);

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            final Exo5ViewsManager viewsManager = new Exo5ViewsManager();
            viewsManager.showFrames(textModel, textServices);
        });
    }
}
