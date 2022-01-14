package com.supinfo.java.day2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListeningAction extends JFrame {
    public ListeningAction() {
        super("Listening");

        this.initComponents();

        this.setSize(250, 250);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            ListeningAction frame = new ListeningAction();
            frame.setVisible(true);
        });
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        this.initButton(panel);
    }

    private void initButton(JPanel panel) {
        JButton button = new JButton("0");
        panel.add(button);

        ActionListener lister = new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                button.setText(Integer.toString(i));
                System.out.println(i);
            }
        };
        button.addActionListener(lister);
    }
}
