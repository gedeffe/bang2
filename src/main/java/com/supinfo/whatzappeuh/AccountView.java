package com.supinfo.whatzappeuh;

import javax.swing.*;
import java.awt.*;

public class AccountView {

    public void display(JFrame mainFrame) {
        JDialog frame = new JDialog(mainFrame, "Account manager", true);
        frame.setSize(550, 500);
        frame.setLocationRelativeTo(null);
        JPanel panel = panel();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private JPanel panel() {
        // Panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        mainPanel.add(panelLeft);
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(0,1));
        mainPanel.add(panelRight);

        // Table
        Object[] header = { "test" };
        Object[][] data = { { "test" } };
        JTable table = new JTable(data, header);
        JScrollPane scrollPanel = new JScrollPane(table);
        panelLeft.add(scrollPanel);

        // Button +
        JButton add = new JButton("+");
        panelRight.add(add);

        // Button -
        JButton remove = new JButton("-");
        panelRight.add(remove);

        return mainPanel;
    }

}
