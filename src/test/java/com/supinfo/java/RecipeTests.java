package com.supinfo.java;

import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.recipe.RecipeDifficulty;
import com.supinfo.recipe.recipe.RecipeModel;
import com.supinfo.recipe.recipe.RecipeSortType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RecipeTests {
    @Test
    public void testSortByName() {
        Recipe recipe = new Recipe("test", "test", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);
        Recipe recipe2 = new Recipe("test2", "test2", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.MEDIUM);
        Recipe recipe3 = new Recipe("test3", "test3", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);

        RecipeModel recipeModel = new RecipeModel();
        recipeModel.addRecipe(recipe);
        recipeModel.addRecipe(recipe2);
        recipeModel.addRecipe(recipe3);

        List<Recipe> recipes = recipeModel.listRecipes(RecipeSortType.NAME);

        Assertions.assertEquals(recipes.get(0).getName(), "test");
        Assertions.assertEquals(recipes.get(1).getName(), "test2");
        Assertions.assertEquals(recipes.get(2).getName(), "test3");
    }

    @Test
    public void testSortByDuration() {
        Recipe recipe = new Recipe("test", "test", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);
        Recipe recipe2 = new Recipe("test2", "test2", List.of(), List.of(), List.of(), 1, 2, RecipeDifficulty.MEDIUM);
        Recipe recipe3 = new Recipe("test3", "test3", List.of(), List.of(), List.of(), 1, 3, RecipeDifficulty.EASY);

        RecipeModel recipeModel = new RecipeModel();
        recipeModel.addRecipe(recipe);
        recipeModel.addRecipe(recipe2);
        recipeModel.addRecipe(recipe3);

        List<Recipe> recipes = recipeModel.listRecipes(RecipeSortType.DURATION);

        Assertions.assertEquals(recipes.get(0).getName(), "test");
        Assertions.assertEquals(recipes.get(1).getName(), "test2");
        Assertions.assertEquals(recipes.get(2).getName(), "test3");
    }

    @Test
    public void testSortByDifficulty() {
        Recipe recipe = new Recipe("test", "test", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);
        Recipe recipe2 = new Recipe("test2", "test2", List.of(), List.of(), List.of(), 1, 2, RecipeDifficulty.MEDIUM);
        Recipe recipe3 = new Recipe("test3", "test3", List.of(), List.of(), List.of(), 1, 3, RecipeDifficulty.EASY);

        RecipeModel recipeModel = new RecipeModel();
        recipeModel.addRecipe(recipe);
        recipeModel.addRecipe(recipe2);
        recipeModel.addRecipe(recipe3);
        
        List<Recipe> recipes = recipeModel.listRecipes(RecipeSortType.DIFFICULTY);

        Assertions.assertEquals(recipes.get(0).getName(), "test");
        Assertions.assertEquals(recipes.get(1).getName(), "test3");
        Assertions.assertEquals(recipes.get(2).getName(), "test2");
    }
}
