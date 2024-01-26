package com.supinfo.recipe.recipe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class RecipeCard extends BorderPane {

    @FXML
    private Text recipeName;

    @FXML
    private Text recipeDescription;

    @FXML
    private Button recipeDetailsButton;

    public RecipeCard(Recipe recipe) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(RecipeCard.this);

        try {
            fxmlLoader.load();
            recipeName.setText(recipe.getName());
            recipeDescription.setText(recipe.getDescription());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void handleRecipeDetailsButtonAction() {
        System.out.println("Recipe details button clicked");
    }

}