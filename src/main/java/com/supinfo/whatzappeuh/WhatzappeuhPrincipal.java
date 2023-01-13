package com.supinfo.whatzappeuh;

import javax.swing.*;

public class WhatzappeuhPrincipal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Frame
            JFrame frame = new JFrame("Whatzappeuh");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

            frame.setVisible(true);
        });
    }
}
