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
}
