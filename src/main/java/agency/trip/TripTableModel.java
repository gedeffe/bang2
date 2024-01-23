package agency.trip;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TripTableModel extends AbstractTableModel {
    private final List<Trip> trips = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return trips.size();
    }


    @Override
    public String getColumnName(int col) {

        return switch (col) {
            case 0 -> "ID";
            case 1 -> "From";
            case 2 -> "To";
            case 3 -> "Price";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Trip trip = trips.get(row);
        return switch (col) {
            case 0 -> trip.getId();
            case 1 -> trip.getFrom();
            case 2 -> trip.getTo();
            case 3 -> trip.getPrice();
            default -> "";
        };
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);

        fireTableDataChanged();
    }

    public void addTrips(List<Trip> tripList) {
        this.trips.addAll(tripList);
        this.fireTableDataChanged();
    }
}
