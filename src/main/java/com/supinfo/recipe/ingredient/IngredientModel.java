package com.supinfo.recipe.ingredient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class IngredientModel {
    private final List<Ingredient> ingredients;

    public void addRecipe(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void deleteRecipe(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void updateRecipe(Ingredient ingredient) {
        ingredients.set(ingredients.indexOf(ingredient), ingredient);
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
