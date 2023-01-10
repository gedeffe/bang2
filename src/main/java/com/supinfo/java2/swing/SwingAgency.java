package com.supinfo.java2.swing;

import javax.swing.*;

public class SwingAgency {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing agency");

        PlaceView placeView = new PlaceView();
        frame.add(placeView.getRootPanel());

        frame.setSize(800, 600);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
