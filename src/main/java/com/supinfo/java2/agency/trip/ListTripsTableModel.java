package com.supinfo.java2.agency.trip;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ListTripsTableModel extends DefaultTableModel implements TripEvents {
    public ListTripsTableModel(List<Trip> tripList, TripEventsSubscriber tripEventsSubscriber) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Identifier");
        columnNames.add("From");
        columnNames.add("To");
        columnNames.add("Price");
        this.setColumnIdentifiers(columnNames);

        for (Trip trip : tripList) {
            this.addRow(trip);
        }
        tripEventsSubscriber.subscribe(this);
    }

    private void addRow(Trip trip) {
        this.addRow(new Object[]{trip.getId(), trip.getDepartureId(), trip.getDestinationId(), trip.getPrice()});
    }

    @Override
    public void notifyTripAdded(Trip newTrip) {
        this.addRow(newTrip);
    }


    @Override
    public void notifyTripRemoved(Trip removedTrip) {
        int rowIndex = -1;

        for (int row = 0; row < dataVector.size() && rowIndex == -1; row++) {
            if (removedTrip.getId().equals(this.getValueAt(row, 0))) {
                rowIndex = row;
            }
        }
        if (rowIndex != -1) {
            this.removeRow(rowIndex);
        }
    }
}
