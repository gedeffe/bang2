package agency.trip;

import agency.place.Place;
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
