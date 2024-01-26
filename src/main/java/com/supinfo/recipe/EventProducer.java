package com.supinfo.recipe;

public interface EventProducer<T> {
    public void subscribe(T listener);
}
