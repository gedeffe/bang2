package com.supinfo.recipe;

import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.recipe.RecipeDifficulty;
import com.supinfo.recipe.recipe.RecipeListDisplayController;
import com.supinfo.recipe.recipe.RecipeModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeDisplayController implements Initializable {

    @FXML
    private RecipeListDisplayController recipeListViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecipeModel recipeModel = new RecipeModel();
        initializeSampleData(recipeModel);
        this.recipeListViewController.setRecipeModel(recipeModel);
    }

    private void initializeSampleData(RecipeModel recipeModel) {
        Recipe recipe = new Recipe("tarte a Tom", "test", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);
        Recipe recipe2 = new Recipe("Tomme de Tom", "test2", List.of(), List.of(), List.of(), 1, 2, RecipeDifficulty.MEDIUM);
        Recipe recipe3 = new Recipe("crumble de Tom", "test3", List.of(), List.of(), List.of(), 1, 3, RecipeDifficulty.EASY);
        recipeModel.addRecipe(recipe);
        recipeModel.addRecipe(recipe2);
        recipeModel.addRecipe(recipe3);
    }
}
