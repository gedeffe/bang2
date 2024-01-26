package com.supinfo.recipe.ingredient.event;

import com.supinfo.recipe.common.DefaultEventProducer;
import com.supinfo.recipe.common.EventProducer;
import com.supinfo.recipe.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientEventProducer extends DefaultEventProducer<IngredientEventListener> implements IngredientEventListener {
    @Override
    public void onCreated(Ingredient ingredient) {
        this.getListeners().forEach(listener -> listener.onCreated(ingredient));
    }

    @Override
    public void onDeleted(Ingredient ingredient) {
        this.getListeners().forEach(listener -> listener.onDeleted(ingredient));
    }
}
