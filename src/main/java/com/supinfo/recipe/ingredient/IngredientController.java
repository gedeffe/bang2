package com.supinfo.recipe.ingredient;

import com.supinfo.recipe.ingredient.event.IngredientEventProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IngredientController {
    private final IngredientModel model;
    private final IngredientEventProducer eventProducer;

    public void addIngredient(Ingredient ingredient) {
        model.addIngredient(ingredient);
        eventProducer.onCreated(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        model.deleteIngredient(ingredient);
        eventProducer.onDeleted(ingredient);
    }
}
