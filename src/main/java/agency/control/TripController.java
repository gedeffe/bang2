package agency.control;

import agency.data.Place;
import agency.data.Trip;
import agency.model.TripModel;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class TripController {

    private final TripModel tripModel;

    public void createTrip(Place from, Place to, double price) {
        if (from != null && to != null && price > 0) {

            Trip trip = new Trip();
            trip.setId(UUID.randomUUID());
            trip.setFrom(from);
            trip.setTo(to);
            trip.setPrice(price);

            tripModel.addTrip(trip);
        }
    }
}
