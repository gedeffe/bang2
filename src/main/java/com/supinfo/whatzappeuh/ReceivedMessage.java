package com.supinfo.whatzappeuh;

import javax.swing.*;
import java.awt.*;

public class ReceivedMessage{

    public static void createMessageArea(JFrame frame){
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane messagePane = new JScrollPane(messageArea);
        frame.add(messagePane, BorderLayout.CENTER);
    }
    
}

