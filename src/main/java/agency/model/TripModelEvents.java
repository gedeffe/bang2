package agency.model;

import agency.data.Trip;

public interface TripModelEvents {
    void onTripCreated(Trip trip);
}
