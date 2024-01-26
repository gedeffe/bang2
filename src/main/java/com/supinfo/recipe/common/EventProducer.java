package com.supinfo.recipe.common;

public interface EventProducer<T> {
    public void subscribe(T listener);
}
