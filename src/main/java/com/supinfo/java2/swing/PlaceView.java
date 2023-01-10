package com.supinfo.java2.swing;

import javax.swing.*;
import java.awt.*;

public class PlaceView {
    private JTextField placeNameTextField;
    private JPanel panel1;
    private JButton button1;

    public JPanel getRootPanel() {
        if (this.panel1 == null) {
            this.panel1 = new JPanel();
            this.panel1.setLayout(new BorderLayout());

            this.placeNameTextField = new JTextField();
            this.panel1.add(this.placeNameTextField, BorderLayout.CENTER);

            this.button1 = new JButton();
            this.panel1.add(this.button1, BorderLayout.EAST);
        }
        return this.panel1;
    }
}
