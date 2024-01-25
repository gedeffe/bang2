package com.supinfo.recipe.ingredient.panels;

import com.supinfo.recipe.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IngredientModel {
    List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void deleteIngredient(int index) {
        this.ingredients.remove(index);
    }

    public void updateIngredien(Ingredient ingredient) {
        this.ingredients.set(ingredients.indexOf(ingredient), ingredient);
    }

    public Ingredient getIngredient(int index) {
        return this.ingredients.get(index);
    }

    public Iterator<Ingredient> iterIngredient() {
        return this.ingredients.iterator();
    }
}
