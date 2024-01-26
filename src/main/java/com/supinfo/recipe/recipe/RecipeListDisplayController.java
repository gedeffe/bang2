package com.supinfo.recipe.recipe;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class RecipeListDisplayController {

    @FXML
    private ChoiceBox<String> choiceBox1;

    @FXML
    private ChoiceBox<String> choiceBox2;

    @FXML
    private ChoiceBox<String> choiceBox3;

    @FXML
    private VBox recipeList;

    private RecipeModel recipeModel;

    @FXML
    public void initialize() {
        choiceBox1.getItems().addAll("asc", "desc");
        choiceBox1.setValue("asc");

        choiceBox2.getItems().addAll("asc", "desc");
        choiceBox2.setValue("asc");

        // for each enum of RecipeDifficulty, add the name to the choiceBox3
        for (RecipeDifficulty recipeDifficulty : RecipeDifficulty.values()) {
            choiceBox3.getItems().add(recipeDifficulty.getDisplayName());
        }
        choiceBox3.setValue(RecipeDifficulty.values()[0].getDisplayName());
    }

    public void setRecipeModel(RecipeModel recipeModel) {
        this.recipeModel = recipeModel;
        this.recipeModel.listRecipes(RecipeSortType.NAME).forEach(recipe -> recipeList.getChildren().add(new RecipeCard(recipe)));
    }
}
