package com.supinfo.whatzappeuh;

import com.supinfo.whatzappeuh.send.UserSendingMessageInterface;

import javax.swing.*;

public class WhatzappeuhPrincipal {

    //private SendingMessageInterface sendingMessageInterface = new SendingMessageInterface();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Whatzappeuh");
            UserSendingMessageInterface placeView = new UserSendingMessageInterface();

            frame.add(placeView.sendingMessageInterface());
            //frame.pack();

            frame.setSize(300, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLayout(null);
            frame.setVisible(true);
        });
    }
}
