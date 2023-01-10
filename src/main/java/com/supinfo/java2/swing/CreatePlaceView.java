package com.supinfo.java2.swing;

import com.supinfo.java2.agency.place.PlaceController;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlaceView {
    private final PlaceController placeController;
    private JTextField placeNameTextField;
    @Getter
    private JPanel rootPanel;
    private JButton addPlaceButton;
    private JLabel placeNameLabel;
    private JPanel placeNamePanel;

    public CreatePlaceView(PlaceController placeControllerParam) {
        this.placeController = placeControllerParam;
        this.initRootPanel();
    }

    private final JPanel initRootPanel() {
        this.rootPanel = new JPanel();
        this.rootPanel.setLayout(new BorderLayout());

        this.rootPanel.add(this.initPlaceName(), BorderLayout.CENTER);
        this.rootPanel.add(this.initAddPlace(), BorderLayout.EAST);

        return this.rootPanel;
    }

    private JComponent initAddPlace() {
        this.addPlaceButton = new JButton("Add Place");
        this.addPlaceButton.addActionListener((actionEvent) -> createPlace());
        return this.addPlaceButton;
    }

    private void createPlace() {
        System.out.println("Call control layer !");
        String name = this.placeNameTextField.getText();
        try {
            this.placeController.addPlace(name);
            // clean input value
            this.placeNameTextField.setText("");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this.getRootPanel(), e.getMessage(), "Place creation error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JComponent initPlaceName() {
        this.placeNamePanel = new JPanel();

        this.placeNameLabel = new JLabel("Name of Place:");
        this.placeNamePanel.add(this.placeNameLabel);

        this.placeNameTextField = new JTextField();
        this.placeNameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePlaceView.this.createPlace();
            }
        });
        this.placeNameTextField.setColumns(50);
        this.placeNamePanel.add(this.placeNameTextField);

        return this.placeNamePanel;
    }

}
