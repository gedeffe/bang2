package com.supinfo.java2.agency.trip;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GlobalTripPanel extends JPanel {

    public GlobalTripPanel(TripModel tripModel) throws SQLException {
        super(new BorderLayout());

        ListTripsTableModel listTripsTableModel = new ListTripsTableModel(tripModel.findAll(), tripModel);
        ListTripsView listTripsView = new ListTripsView(listTripsTableModel);
        this.add(listTripsView, BorderLayout.CENTER);
    }
}
