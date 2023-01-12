package com.supinfo.java2.agency.place;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ListPlacesTableModel extends DefaultTableModel implements PlaceEvents {
    public ListPlacesTableModel(List<Place> placeList, PlaceEventsSubscriber placeEventsSubscriber) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Identifier");
        columnNames.add("Place's name");
        this.setColumnIdentifiers(columnNames);

        for (Place place : placeList) {
            this.addRow(place);
        }
        placeEventsSubscriber.subscribe(this);
    }

    private void addRow(Place place) {
        this.addRow(new Object[]{place.getId(), place.getName()});
    }

    @Override
    public void notifyPlaceAdded(Place newPlace) {
        this.addRow(newPlace);
    }

    @Override
    public void notifyPlaceUpdated(Place updatedPlace) {

    }

    @Override
    public void notifyPlaceRemoved(Place removedPlace) {
        int rowIndex = -1;

        for (int row = 0; row < dataVector.size() && rowIndex == -1; row++) {
            if (removedPlace.getId().equals(this.getValueAt(row, 0))) {
                rowIndex = row;
            }
        }
        if (rowIndex != -1) {
            this.removeRow(rowIndex);
        }
    }
}
