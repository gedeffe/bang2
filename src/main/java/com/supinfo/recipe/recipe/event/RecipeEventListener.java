package com.supinfo.recipe.recipe.event;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;

public interface RecipeEventListener {
    public void onCreated(Recipe recipe);
    public void onDeleted(Recipe recipe);
}
