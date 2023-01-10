package com.supinfo.java2.agency.place;

public interface PlaceEvents {
    void notifyPlaceAdded(Place newPlace);

    void notifyPlaceUpdated(Place updatedPlace);

    void notifyPlaceRemoved(Place removedPlace);
}
