package com.supinfo.recipe.ingredient.event;

import com.supinfo.recipe.ingredient.Ingredient;

public interface IngredientEventListener {
    public void onCreated(Ingredient ingredient);
    public void onDeleted(Ingredient ingredient);
}
