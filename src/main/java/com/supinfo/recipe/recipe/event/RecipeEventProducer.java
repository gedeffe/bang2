package com.supinfo.recipe.recipe.event;

import com.supinfo.recipe.common.DefaultEventProducer;
import com.supinfo.recipe.common.EventProducer;
import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.Step;
import com.supinfo.recipe.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class RecipeEventProducer extends DefaultEventProducer<RecipeEventListener> implements RecipeEventListener {

    @Override
    public void onCreated(Recipe recipe) {
        this.getListeners().forEach(listener -> listener.onCreated(recipe));
    }

    @Override
    public void onDeleted(Recipe recipe) {
        this.getListeners().forEach(listener -> listener.onDeleted(recipe));
    }

    @Override
    public void onIngredientAdded(Recipe recipe, Ingredient ingredient) {
        this.getListeners().forEach(listener -> listener.onIngredientAdded(recipe, ingredient));
    }

    @Override
    public void onIngredientRemoved(Recipe recipe, Ingredient ingredient) {
        this.getListeners().forEach(listener -> listener.onIngredientRemoved(recipe, ingredient));
    }

    @Override
    public void onStepAdded(Recipe recipe, Step step) {
        this.getListeners().forEach(listener -> listener.onStepAdded(recipe, step));
    }

    @Override
    public void onStepRemoved(Recipe recipe, Step step) {
        this.getListeners().forEach(listener -> listener.onStepRemoved(recipe, step));
    }

    @Override
    public void onToolAdded(Recipe recipe, Tool tool) {
        this.getListeners().forEach(listener -> listener.onToolAdded(recipe, tool));
    }

    @Override
    public void onToolRemoved(Recipe recipe, Tool tool) {
        this.getListeners().forEach(listener -> listener.onToolRemoved(recipe, tool));
    }
}
