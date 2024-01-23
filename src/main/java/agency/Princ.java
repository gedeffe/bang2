package agency;

import agency.common.database.DbTools;
import agency.place.PlaceController;
import agency.place.PlaceCreationComponent;
import agency.place.PlaceModel;
import agency.trip.TripAgency;
import agency.trip.TripController;
import agency.trip.TripCreationComponent;
import agency.trip.TripModel;

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
        PlaceCreationComponent placeCreationComponent = new PlaceCreationComponent(placeController);
        placeModel.subscribe(placeCreationComponent);
        TripCreationComponent tripCreationComponent = new TripCreationComponent(placeModel, tripModel, tripController);

        SwingUtilities.invokeLater(() -> {
            TripAgency tripAgency = new TripAgency(tripModel);

            tripAgency.displayFrame(placeCreationComponent, tripCreationComponent);


        });
    }
}
