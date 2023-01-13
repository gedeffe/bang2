package com.supinfo.whatzappeuh.send;

import javax.swing.*;
import java.awt.*;

public class UserSendingMessageInterface {

    static JFrame frame;
    private JButton sendButton;

    private JTextField messageField;

    private JPanel panelMessageInterface;


    public JPanel sendingMessageInterface() {
        if (this.panelMessageInterface == null) {
            this.panelMessageInterface = new JPanel();

            UserSendingMessageInterface placeView = new UserSendingMessageInterface();


            this.messageField = (new JTextField(""));
            this.messageField.setColumns(20);
            this.panelMessageInterface.add(this.messageField, BorderLayout.CENTER);
            
            this.sendButton = (new JButton(("send")));
            this.panelMessageInterface.add(this.sendButton, BorderLayout.EAST);


            this.panelMessageInterface.setVisible(true);


        }


        return this.panelMessageInterface;


    }


}






