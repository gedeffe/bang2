package com.supinfo.java.day2.exo5.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class of data layer (model).
 */
public class TextModelImpl implements TextModel {

    private final List<TextEvents> listeners = new ArrayList<>();
    private String textData = "";

    @Override
    public void updateTextData(final String text) {
        // here we might store our data to a persistence layer
        // won't do it for this tiny example
        this.textData = text;
        // send a events to update listeners
        this.listeners.forEach((listener) -> listener.notifyTextModified(text));
    }

    @Override
    public String getTextData() {
        return this.textData;
    }

    @Override
    public void register(final TextEvents listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    @Override
    public void unregister(final TextEvents listener) {
        this.listeners.remove(listener);
    }
}
