package agency.trip;

import agency.place.Place;
import agency.place.PlaceModel;
import agency.place.PlaceModelEvents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TripCreationComponent extends JPanel implements PlaceModelEvents, TripModelEvents {
    private final DefaultComboBoxModel<Place> fromComboBoxModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<Place> toComboBoxModel = new DefaultComboBoxModel<>();
    private final TripController tripController;
    private SpinnerModel spinnerModel;

    public TripCreationComponent(PlaceModel placeModel, TripModel tripModel, TripController tripController) {
        super(new BorderLayout());
        placeModel.subscribe(this);
        tripModel.subscribe(this);

        this.tripController = tripController;

        // initialize places combo boxes
        List<Place> placeList = placeModel.getPlaces();
        this.fromComboBoxModel.addAll(placeList);
        this.toComboBoxModel.addAll(placeList);

        this.initializeAddTripComponent();
    }

    private void initializeAddTripComponent() {
        Border blackline = BorderFactory.createTitledBorder("Ajouter un voyage");
        this.setBorder(blackline);


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

        spinnerModel = new SpinnerNumberModel(0, 0, 1000, 0.1);
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

        this.add(button, BorderLayout.EAST);

        this.add(insidePanel, BorderLayout.CENTER);
    }


    @Override
    public void onTripCreated(Trip trip) {
        // reset trip fields
        this.spinnerModel.setValue(0);
        this.fromComboBoxModel.setSelectedItem(null);
        this.toComboBoxModel.setSelectedItem(null);
    }

    @Override
    public void onPlaceCreated(Place place) {
        fromComboBoxModel.addElement(place);
        toComboBoxModel.addElement(place);
    }
}
