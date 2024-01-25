package com.supinfo.recipe.ingredient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class IngredientModel {
    private final List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void updateIngredient(Ingredient ingredient) {
        ingredients.set(ingredients.indexOf(ingredient), ingredient);
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
