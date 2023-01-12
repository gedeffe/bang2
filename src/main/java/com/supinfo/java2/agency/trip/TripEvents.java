package com.supinfo.java2.agency.trip;

public interface TripEvents {
    void notifyTripAdded(Trip newTrip);


    void notifyTripRemoved(Trip removedTrip);
}
