package com.supinfo.java2.agency.place;

public interface PlaceEventsSubscriber {
    void subscribe(PlaceEvents listener);

    void unsubscribe(PlaceEvents listener);
}
