package com.supinfo.java.day2.exo5.view;


import com.supinfo.java.day2.exo5.control.TextServices;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * To let the user enter custom text to add, and buttons to manage text content (send new text or clear existing text).
 */
public class CommandsAreaWindow extends JFrame {

    private final TextServices textServices;
    private JTextField userInput;

    public CommandsAreaWindow(final TextServices textServicesParam) {
        super("Text editor commands");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textServices = textServicesParam;

        this.initComponents();

        // retrieve current height to keep it
        this.pack();
        int height = (int) this.getSize().getHeight();
        // force width to fill whole screen
        int targetWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        // change current size
        this.setSize(targetWidth, height);
    }

    private void initComponents() {
        // content panel
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.getContentPane().add(panel);

        // user text entry
        final int columns = 100; // to help our component to have a size enough to enter some text easily
        this.userInput = new JTextField(columns);
        this.userInput.addActionListener((event) -> this.sendText());
        panel.add(this.userInput);

        // command buttons
        final JPanel commandsPanel = new JPanel();
        final JButton sendButton = new JButton("Send");
        sendButton.addActionListener((event) -> this.sendText());
        commandsPanel.add(sendButton);

        final JButton clearButton = new JButton("Clear");
        clearButton.addActionListener((event) -> this.textServices.clearText());
        commandsPanel.add(clearButton);

        panel.add(commandsPanel);
    }

    private void sendText() {
        if (this.textServices != null) {
            // retrieve the text from user input and send it to our service layer.
            final String text = this.userInput.getText();
            this.textServices.addText(text);
            // clear user text
            this.userInput.setText("");
        }
    }
}
