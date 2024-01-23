package agency;

import agency.common.database.DbTools;
import agency.place.PlaceController;
import agency.place.PlaceModel;
import agency.trip.TripController;
import agency.trip.TripModel;
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
        TripController tripController = new TripController(tripModel);

        // view layer
        SwingUtilities.invokeLater(() -> {
            TripAgency tripAgency = new TripAgency(placeModel, tripModel, placeController, tripController);

            tripAgency.displayFrame();


        });
    }
}
