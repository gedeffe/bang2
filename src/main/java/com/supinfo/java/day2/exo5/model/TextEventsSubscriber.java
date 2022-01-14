package com.supinfo.java.day2.exo5.model;

/**
 * To register a listener to text events producer.
 */
public interface TextEventsSubscriber {
    /**
     * Subscribe to new events.
     *
     * @param listener notified on new events.
     */
    void register(TextEvents listener);

    /**
     * Un-subscribe from new events.
     *
     * @param listener to remove from notified elements.
     */
    void unregister(TextEvents listener);
}
