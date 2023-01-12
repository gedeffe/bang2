package com.supinfo.java2.agency.trip;

import com.supinfo.java2.agency.place.Place;
import com.supinfo.java2.agency.place.PlaceModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class AddTripDialogPanel extends JPanel {

    private final TripController tripController;
    private JComboBox<Place> fromComboBox;
    private JComboBox<Place> destinationComboBox;
    private JTextField priceTextField;

    public AddTripDialogPanel(JFrame frame, PlaceModel placeModel, TripController tripControllerParam) {
        super(new BorderLayout());

        this.tripController = tripControllerParam;

        JDialog dialog = new JDialog(frame, "Add Trip", true);
        JPanel tripPanel = this.initTripPanel(dialog, placeModel);

        dialog.add(tripPanel);

        dialog.pack();
        dialog.setVisible(true);
    }

    private JPanel initTripPanel(JDialog dialog, PlaceModel placeModel) {
        JPanel tripPanel = new JPanel(new BorderLayout());

        JPanel tripInformationPanel = this.initTripInformationPanel(placeModel);
        tripPanel.add(tripInformationPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = this.initActionsPanel(dialog);
        tripPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return tripPanel;
    }

    private JPanel initActionsPanel(JDialog dialog) {
        JPanel actionsPanel = new JPanel();

        JButton cancelButton = new JButton("Cancel");
        actionsPanel.add(cancelButton);
        cancelButton.addActionListener((actionEvent) -> dialog.setVisible(false));

        JButton createButton = new JButton("Add Trip");
        actionsPanel.add(createButton);
        createButton.addActionListener((actionEvent) -> AddTripDialogPanel.this.createTrip(dialog));

        return actionsPanel;
    }

    private void createTrip(JDialog dialog) {
        Place from = (Place) this.fromComboBox.getSelectedItem();
        Place destination = (Place) this.destinationComboBox.getSelectedItem();
        String priceText = this.priceTextField.getText();

        if (from != null && destination != null) {
            try {
                double price = Double.parseDouble(priceText);
                this.tripController.addTrip(from.getId(), destination.getId(), price);
                dialog.setVisible(false);
                dialog.dispose();
            } catch (NumberFormatException | NullPointerException exception) {
                JOptionPane.showMessageDialog(this, "Error", "Please, enter a valid price !", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error", "Please, select a departure and a destination !", JOptionPane.ERROR_MESSAGE);
        }
    }


    private JPanel initTripInformationPanel(PlaceModel placeModel) {
        JPanel tripInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        //natural height, maximum width
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        List<Place> placeList;
        try {
            placeList = placeModel.findAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error", "Check your database access: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            placeList = Collections.emptyList();
        }

        JLabel fromLabel = new JLabel("From:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        tripInfoPanel.add(fromLabel, gridBagConstraints);
        Vector<Place> fromComboBoxModel = new Vector<>(placeList);
        fromComboBox = new JComboBox<>(fromComboBoxModel);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        tripInfoPanel.add(fromComboBox, gridBagConstraints);

        JLabel destinationLabel = new JLabel("To:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        tripInfoPanel.add(destinationLabel, gridBagConstraints);
        Vector<Place> destinationComboBoxModel = new Vector<>(placeList);
        destinationComboBox = new JComboBox<>(destinationComboBoxModel);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 2;
        tripInfoPanel.add(destinationComboBox, gridBagConstraints);

        JLabel priceLabel = new JLabel("Price:");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        tripInfoPanel.add(priceLabel, gridBagConstraints);
        priceTextField = new JTextField();
        this.priceTextField.setColumns(10);
        gridBagConstraints.gridx = 1;
        tripInfoPanel.add(priceTextField, gridBagConstraints);
        JLabel euroLabel = new JLabel("€");
        gridBagConstraints.gridx = 2;
        tripInfoPanel.add(euroLabel, gridBagConstraints);
        return tripInfoPanel;
    }
}
