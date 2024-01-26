package com.supinfo.recipe.ingredient.event;

import com.supinfo.recipe.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientEventProducer implements IngredientEventListener{
    List<IngredientEventListener> listeners = new ArrayList<>();

    public void subscribe(IngredientEventListener listener) {
        if (listener == this || this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
    }

    @Override
    public void onCreated(Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onCreated(ingredient));
    }

    @Override
    public void onDeleted(Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onDeleted(ingredient));
    }
}
