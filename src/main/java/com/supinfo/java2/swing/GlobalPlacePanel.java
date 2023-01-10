package com.supinfo.java2.swing;

import com.supinfo.java2.agency.place.PlaceController;
import com.supinfo.java2.agency.place.PlaceModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GlobalPlacePanel extends JPanel {

    public GlobalPlacePanel(PlaceController placeController, PlaceModel placeModel) throws SQLException {
        super(new BorderLayout());

        CreatePlaceView createPlaceView = new CreatePlaceView(placeController);
        this.add(createPlaceView.getRootPanel(), BorderLayout.NORTH);

        ListPlacesTableModel listPlacesTableModel = new ListPlacesTableModel(placeModel.findAll(), placeModel);
        ListPlacesView listPlacesView = new ListPlacesView(listPlacesTableModel);
        this.add(listPlacesView.getRootPanel(), BorderLayout.CENTER);
    }


}
