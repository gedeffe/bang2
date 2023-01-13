package com.supinfo.whatzappeuh;

import javax.swing.*;

public class MenuBar {
    
    public static void display(JFrame frame) {
        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Menu
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        //MenuItems
        JMenuItem accountManagement = new JMenuItem("Account management");
        JMenuItem selectRecipient = new JMenuItem("Select recipient");
        menu.add(accountManagement);
        accountManagement.addActionListener((actionEvent)->AccountView.display(frame));
        menu.add(selectRecipient);
    }
}
