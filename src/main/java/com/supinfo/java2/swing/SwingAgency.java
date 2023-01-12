package com.supinfo.java2.swing;

import com.supinfo.java2.agency.place.GlobalPlacePanel;
import com.supinfo.java2.agency.place.PlaceController;
import com.supinfo.java2.agency.place.PlaceModel;
import com.supinfo.java2.agency.trip.AddTripDialogPanel;
import com.supinfo.java2.agency.trip.GlobalTripPanel;
import com.supinfo.java2.agency.trip.TripController;
import com.supinfo.java2.agency.trip.TripModel;

import javax.swing.*;
import java.sql.SQLException;

public class SwingAgency {

    public static void main(String[] args) throws SQLException {
        PlaceModel placeModel = new PlaceModel();
        PlaceController placeController = new PlaceController(placeModel);

        GlobalPlacePanel globalPlacePanel = new GlobalPlacePanel(placeController, placeModel);

        TripModel tripModel = new TripModel();
        TripController tripController = new TripController(tripModel);
        GlobalTripPanel globalTripPanel = new GlobalTripPanel(tripModel);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Swing agency");

            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    globalPlacePanel, globalTripPanel);
            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerLocation(500);
            frame.add(splitPane);

            // menu
            JMenu menu = new JMenu("Commands");
            JMenuItem tripMenu = new JMenuItem("Add Trip");
            tripMenu.addActionListener((actionEvent) -> new AddTripDialogPanel(frame, placeModel, tripController));
            menu.add(tripMenu);

            JMenuItem exitMenu = new JMenuItem("Quit");
            exitMenu.addActionListener((actionEvent) -> System.exit(0));
            menu.add(exitMenu);

            JMenuBar menuBar = new JMenuBar();
            menuBar.add(menu);
            frame.setJMenuBar(menuBar);

            // size and visibility
            frame.setSize(1024, 600);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
