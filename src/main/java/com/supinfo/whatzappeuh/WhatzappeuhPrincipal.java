package com.supinfo.whatzappeuh;

import javax.swing.*;

public class WhatzappeuhPrincipal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Whatzappeuh");
            ReceivedMessage.createMessageArea(frame);

            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setSize(800, 800);
            
        });
    }
}
