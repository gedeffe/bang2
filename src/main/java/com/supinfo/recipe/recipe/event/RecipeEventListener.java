package com.supinfo.recipe.recipe.event;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;

public interface RecipeEventListener {
    public void onCreated(Recipe recipe);
    public void onDeleted(Recipe recipe);
    public void onIngredientAdded(Recipe recipe, Ingredient ingredient);
    public void onIngredientRemoved(Recipe recipe, Ingredient ingredient);
    public void onStepAdded(Recipe recipe, Step step);
    public void onStepRemoved(Recipe recipe, Step step);
    public void onToolAdded(Recipe recipe, Tool tool);
    public void onToolRemoved(Recipe recipe, Tool tool);
}
