package com.supinfo.recipe.common;

import java.util.Iterator;

public interface DataModel<T> {
    public void add(T item);
    public void delete(T item);
    public void update(T item);
    public T get(int index);
    public Iterator<T> iterator();
}
