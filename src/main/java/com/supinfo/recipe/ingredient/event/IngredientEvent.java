package com.supinfo.recipe.ingredient.event;

import com.supinfo.recipe.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientEvent {
    List<IngredientEventListener> listeners = new ArrayList<>();

    public void subscribe(IngredientEventListener listener) {
        this.listeners.add(listener);
    }

    public void onCreate(Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onCreated(ingredient));
    }

    public void onDeleted(Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onDeleted(ingredient));
    }
}
