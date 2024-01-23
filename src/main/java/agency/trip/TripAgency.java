package agency.trip;

import agency.place.PlaceCreationComponent;

import javax.swing.*;
import java.awt.*;

public class TripAgency extends JFrame implements TripModelEvents {

    private final TripTableModel tripTableModel = new TripTableModel();


    public TripAgency(TripModel tripModel) {
        // listen to model events to update view components
        tripModel.subscribe(this);

        // initialize trips table model
        this.tripTableModel.addTrips(tripModel.getTrips());
    }

    public void displayFrame(PlaceCreationComponent placeCreationComponent, TripCreationComponent tripCreationComponent) {
        this.setPreferredSize(new Dimension(500, 500));
        // define the default operation when
        // the frame is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // define the title of the frame
        this.setTitle("Trip Agency");

        this.add(placeCreationComponent, BorderLayout.NORTH);

        this.add(tripCreationComponent, BorderLayout.SOUTH);

        JPanel tripsPanel = this.createTripsPanel();
        this.add(tripsPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    private JPanel createTripsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = new JTable(this.tripTableModel);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }


    @Override
    public void onTripCreated(Trip trip) {
        // add to table model
        tripTableModel.addTrip(trip);
    }
}
