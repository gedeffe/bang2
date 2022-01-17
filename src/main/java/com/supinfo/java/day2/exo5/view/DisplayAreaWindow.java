package com.supinfo.java.day2.exo5.view;

import com.supinfo.java.day2.exo5.model.TextEvents;
import com.supinfo.java.day2.exo5.model.TextEventsSubscriber;
import com.supinfo.java.day2.exo5.model.TextModel;

import javax.swing.*;
import java.awt.*;

public class DisplayAreaWindow extends JWindow implements TextEvents {
    private JTextArea textContent;

    public DisplayAreaWindow(final TextModel
                                     textModel) {
        // move location to be under main frame
        this.setLocation(0, 115);

        this.initComponents();
        // retrieve current height to keep it
        this.pack();
        int height = (int) this.getSize().getHeight();
        // force width to fill whole screen
        int targetWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        // change current size
        this.setSize(targetWidth, height);

        // register to application events
        textModel.register(this);

        // initialize text content with existing data
        this.textContent.setText(textModel.getTextData());
    }

    private void initComponents() {
        // add a big text area to display what the user has entered
        final int lines = 50; // should correspond to height
        final int columns = 100; // should correspond to width
        this.textContent = new JTextArea(lines, columns);
        this.getContentPane().add(this.textContent);
    }

    @Override
    public void notifyTextModified(final String text) {
        this.textContent.setText(text);
    }
}
