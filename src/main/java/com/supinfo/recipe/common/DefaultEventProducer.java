package com.supinfo.recipe.common;

import java.util.ArrayList;
import java.util.List;

public class DefaultEventProducer<T> implements EventProducer<T> {
    private final List<T> listeners = new ArrayList<>();
    @Override
    public void subscribe(T listener) {
        if (listener == this || this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    public List<T> getListeners() {
        return this.listeners;
    }
}
