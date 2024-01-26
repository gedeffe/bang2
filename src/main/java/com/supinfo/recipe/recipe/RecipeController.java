package com.supinfo.recipe.recipe;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.step.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecipeController {
    private final RecipeModel recipeModel;

    public void addStepToRecipe(Recipe recipe, Step step) {
        recipe.addStep(step);
        recipeModel.updateRecipe(recipe);
    }

    public void addIngredientToRecipe(Recipe recipe, Ingredient ingredient) {
        recipe.addIngredient(ingredient);
        recipeModel.updateRecipe(recipe);
    }

    public void listRecipes(RecipeSortType sortType) {
        recipeModel.listRecipes(sortType);
    }

}
