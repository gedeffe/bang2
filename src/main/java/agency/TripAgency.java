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


}
