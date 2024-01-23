package agency.place;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlaceCreationComponent extends JPanel implements PlaceModelEvents {

    private final PlaceController placeController;
    private final JTextField placeTextField = new JTextField();

    public PlaceCreationComponent(PlaceController placeController) {
        super(new BorderLayout());
        this.placeController = placeController;
        this.initializeAddPlaceContent();
    }

    private void initializeAddPlaceContent() {
        Border blackline = BorderFactory.createTitledBorder("Ajouter une destination");
        this.setBorder(blackline);

        this.add(placeTextField, BorderLayout.CENTER);
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
        this.add(button, BorderLayout.EAST);
    }

    @Override
    public void onPlaceCreated(Place place) {
        // clear textfield
        this.placeTextField.setText("");
    }
}
