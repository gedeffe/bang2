package com.supinfo.recipe.recipe.event;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class RecipeEvent implements RecipeEventListener {
    List<RecipeEventListener> listeners = new ArrayList<>();

    public void subscribe(RecipeEventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void onCreated(Recipe recipe) {
        this.listeners.forEach(listener -> listener.onCreated(recipe));
    }

    @Override
    public void onDeleted(Recipe recipe) {
        this.listeners.forEach(listener -> listener.onDeleted(recipe));
    }

    @Override
    public void onIngredientAdded(Recipe recipe, Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onIngredientAdded(recipe, ingredient));
    }

    @Override
    public void onIngredientRemoved(Recipe recipe, Ingredient ingredient) {
        this.listeners.forEach(listener -> listener.onIngredientRemoved(recipe, ingredient));
    }

    @Override
    public void onStepAdded(Recipe recipe, Step step) {
        this.listeners.forEach(listener -> listener.onStepAdded(recipe, step));
    }

    @Override
    public void onStepRemoved(Recipe recipe, Step step) {
        this.listeners.forEach(listener -> listener.onStepRemoved(recipe, step));
    }

    @Override
    public void onToolAdded(Recipe recipe, Tool tool) {
        this.listeners.forEach(listener -> listener.onToolAdded(recipe, tool));
    }

    @Override
    public void onToolRemoved(Recipe recipe, Tool tool) {
        this.listeners.forEach(listener -> listener.onToolRemoved(recipe, tool));
    }
}
