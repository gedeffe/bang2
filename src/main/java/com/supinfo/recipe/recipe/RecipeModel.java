package com.supinfo.recipe.recipe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class RecipeModel {

    private final List<Recipe> recipes;

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public void updateRecipe(Recipe recipe) {
        recipes.set(recipes.indexOf(recipe), recipe);
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    public List<Recipe> listRecipes() {
        return recipes;
    }

}
