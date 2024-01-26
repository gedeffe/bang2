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
        this.recipeListViewController.setRecipeModel(recipeModel);
    }
}
