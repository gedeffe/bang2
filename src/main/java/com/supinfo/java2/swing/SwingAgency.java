package com.supinfo.java2.swing;

import com.supinfo.java2.agency.place.PlaceController;
import com.supinfo.java2.agency.place.PlaceModel;

import javax.swing.*;
import java.sql.SQLException;

public class SwingAgency {

    public static void main(String[] args) throws SQLException {
        PlaceModel placeModel = new PlaceModel();
        PlaceController placeController = new PlaceController(placeModel);
        
        GlobalPlacePanel globalPlacePanel = new GlobalPlacePanel(placeController, placeModel);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing agency");

            frame.add(globalPlacePanel);

            frame.setSize(800, 600);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
