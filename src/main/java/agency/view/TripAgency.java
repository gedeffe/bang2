package agency.view;

import agency.control.PlaceController;
import agency.control.TripController;
import agency.data.Place;
import agency.data.Trip;
import agency.model.PlaceModel;
import agency.model.PlaceModelEvents;
import agency.model.TripModel;
import agency.model.TripModelEvents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TripAgency extends JFrame implements PlaceModelEvents, TripModelEvents {

    private final PlaceController placeController;
    private final TripController tripController;
    private final DefaultComboBoxModel<Place> fromComboBoxModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<Place> toComboBoxModel = new DefaultComboBoxModel<>();

    private final TripTableModel tripTableModel = new TripTableModel();

    private final JTextField placeTextField = new JTextField();

    public TripAgency(PlaceModel placeModel, TripModel tripModel, PlaceController placeController, TripController tripController) {
        this.placeController = placeController;
        this.tripController = tripController;
        // listen to model events to update view components
        placeModel.subscribe(this);
        tripModel.subscribe(this);

        // initialize places combo boxes
        List<Place> placeList = placeModel.getPlaces();
        this.fromComboBoxModel.addAll(placeList);
        this.toComboBoxModel.addAll(placeList);

        // initialize trips table model
        this.tripTableModel.addTrips(tripModel.getTrips());
    }

    public void displayFrame() {
        this.setPreferredSize(new Dimension(500, 500));
        // define the default operation when
        // the frame is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // define the title of the frame
        this.setTitle("Trip Agency");

        JPanel addPlacePanel = this.createAddPlace();
        this.add(addPlacePanel, BorderLayout.NORTH);

        JPanel addTripPanel = this.createAddTrip();
        this.add(addTripPanel, BorderLayout.SOUTH);

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

    private JPanel createAddPlace() {
        JPanel panel = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createTitledBorder("Ajouter une destination");
        panel.setBorder(blackline);

        panel.add(placeTextField, BorderLayout.CENTER);
        JButton button = new JButton();
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = placeTextField.getText();
                placeController.createPlace(text);
            }
        };
        action.putValue(AbstractAction.NAME, "Valider");
        button.setAction(action);
        placeTextField.setAction(action);
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JPanel createAddTrip() {
        JPanel panel = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createTitledBorder("Ajouter un voyage");
        panel.setBorder(blackline);


        JComboBox<Place> comboBoxD = new JComboBox<>(fromComboBoxModel);
        JComboBox<Place> comboBoxA = new JComboBox<>(toComboBoxModel);
        JPanel insidePanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        JLabel labelD = new JLabel("From: ");
        c.gridx = 0;
        c.gridy = 0;
        insidePanel.add(labelD, c);

        c.gridx = 1;
        c.gridy = 0;
        insidePanel.add(comboBoxD, c);

        JLabel labelA = new JLabel("To: ");
        c.gridx = 0;
        c.gridy = 1;
        insidePanel.add(labelA, c);

        c.gridx = 1;
        c.gridy = 1;
        insidePanel.add(comboBoxA, c);

        JLabel labelP = new JLabel("Price: ");
        c.gridx = 0;
        c.gridy = 2;
        insidePanel.add(labelP, c);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 1000, 0.1);
        JSpinner sp = new JSpinner(spinnerModel);
        c.gridx = 1;
        c.gridy = 2;
        insidePanel.add(sp, c);

        JButton button = new JButton("Valider");

        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Place from = (Place) comboBoxD.getSelectedItem();
                Place to = (Place) comboBoxA.getSelectedItem();
                double price = (double) spinnerModel.getValue();

                tripController.createTrip(from, to, price);
            }
        };
        action.putValue(AbstractAction.NAME, "Valider");
        button.setAction(action);

        panel.add(button, BorderLayout.EAST);

        panel.add(insidePanel, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void onPlaceCreated(Place place) {
        fromComboBoxModel.addElement(place);
        toComboBoxModel.addElement(place);

        // clear textfield
        this.placeTextField.setText("");
    }

    @Override
    public void onTripCreated(Trip trip) {
        // add to table model
        tripTableModel.addTrip(trip);

        // reset trip fields
    }
}
