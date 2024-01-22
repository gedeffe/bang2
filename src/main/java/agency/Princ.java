package agency;

import agency.control.PlaceController;
import agency.model.PlaceModel;
import agency.model.TripModel;
import agency.model.database.DbTools;
import agency.view.TripAgency;

import javax.swing.*;

public class Princ {
    public static void main(String[] args) {
        // model layer
        DbTools dbTools = new DbTools();
        dbTools.initDatabase();
        PlaceModel placeModel = new PlaceModel(dbTools);
        TripModel tripModel = new TripModel(dbTools, placeModel);

        // control layer
        PlaceController placeController = new PlaceController(placeModel);

        // view layer
        SwingUtilities.invokeLater(() -> {
            TripAgency tripAgency = new TripAgency(placeModel, tripModel, placeController);

            tripAgency.displayFrame();


        });
    }
}
