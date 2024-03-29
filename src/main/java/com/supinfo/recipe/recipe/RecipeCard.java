package com.supinfo.recipe.recipe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

public class RecipeCard extends BorderPane {

    @FXML
    private Text recipeName;

    @FXML
    private Text recipeDescription;

    @FXML
    private Text recipeDuration;

    @FXML
    private Text recipeDifficulty;

    @FXML
    private Button recipeDetailsButton;


    @Getter
    private final Recipe recipe;

    public RecipeCard(Recipe recipe) {
        this.recipe = recipe;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recipe-card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(RecipeCard.this);

        try {
            fxmlLoader.load();
            recipeName.setText(recipe.getName());
            recipeDescription.setText(recipe.getDescription());
            recipeDuration.setText(String.valueOf(recipe.getDuration()));
            recipeDifficulty.setText(recipe.getDifficulty().getDisplayName());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    protected void handleRecipeDetailsButtonAction() {
        System.out.println("Recipe " + this.recipe.getName() + "details button clicked");
    }

}