package agency;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TripAgency extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TripAgency tripAgency = new TripAgency();

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
        JButton button = new JButton("Valider");
        panel.add(button, BorderLayout.EAST);

        return panel;
    }

    private JPanel displayAddTrip() {
        JPanel panel = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createTitledBorder("Ajouter un voyage");
        panel.setBorder(blackline);

        JComboBox comboBoxD = new JComboBox();
        JComboBox comboBoxA = new JComboBox();
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
