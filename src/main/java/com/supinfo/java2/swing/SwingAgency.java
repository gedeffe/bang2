package com.supinfo.java2.swing;

import com.supinfo.java2.agency.place.PlaceController;

import javax.swing.*;

public class SwingAgency {

    public static void main(String[] args) {
        PlaceController placeController = new PlaceController();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing agency");

            CreatePlaceView createPlaceView = new CreatePlaceView(placeController);
            frame.add(createPlaceView.getRootPanel());

            frame.setSize(800, 600);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
