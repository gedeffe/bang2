package agency;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TripAgency extends JFrame {
    private final PlaceModel placeModel;
    private final DefaultComboBoxModel<Place> fromComboBoxModel = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<Place> toComboBoxModel = new DefaultComboBoxModel<>();

    public TripAgency(PlaceModel placeModel) {
        this.placeModel = placeModel;
    }

    public static void main(String[] args) {
        PlaceModel placeModel = new PlaceModel();
        SwingUtilities.invokeLater(() -> {
            TripAgency tripAgency = new TripAgency(placeModel);

            tripAgency.displayFrame();


        });
    }

    public void displayFrame() {
        this.setPreferredSize(new Dimension(500, 500));
        // define the default operation when
        // the frame is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // define the title of the frame
        this.setTitle("Trip Agency");

        JPanel addPlacePanel = this.displayAddPlace();
        this.add(addPlacePanel, BorderLayout.NORTH);

        JPanel addTripPanel = this.displayAddTrip();
        this.add(addTripPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    private JPanel displayAddPlace() {
        JPanel panel = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createTitledBorder("Ajouter une destination");
        panel.setBorder(blackline);
        JTextField textField = new JTextField();

        panel.add(textField, BorderLayout.CENTER);
        JButton button = new JButton();
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (StringUtils.isNotEmpty(text)) {

                    Place place = new Place(text);

                    placeModel.getPlaces().add(place);
                    fromComboBoxModel.addElement(place);
                    toComboBoxModel.addElement(place);
                }
            }
        };
        action.putValue(AbstractAction.NAME, "Valider");
        button.setAction(action);
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JPanel displayAddTrip() {
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

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 1000, 1);
        JSpinner sp = new JSpinner(spinnerModel);
        c.gridx = 1;
        c.gridy = 2;
        insidePanel.add(sp, c);

        JButton button = new JButton("Valider");
        panel.add(button, BorderLayout.EAST);

        panel.add(insidePanel, BorderLayout.CENTER);
        return panel;
    }

}
