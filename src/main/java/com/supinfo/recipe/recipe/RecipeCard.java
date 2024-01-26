package com.supinfo.recipe.recipe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class RecipeCard extends BorderPane {

    @FXML
    private Text recipeName;

    @FXML
    private Text recipeDescription;

    public RecipeCard(Recipe recipe) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(RecipeCard.this);

        try {
            fxmlLoader.load();
            recipeName.setText("Recipe name");
            recipeDescription.setText("Recipe description");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}