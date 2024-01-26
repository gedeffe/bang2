package com.supinfo.recipe.recipe;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RecipeListController {

    @FXML
    private ChoiceBox<String> choiceBox1;

    @FXML
    private ChoiceBox<String> choiceBox2;

    @FXML
    private ChoiceBox<String> choiceBox3;

    @FXML
    private VBox recipeList;



    Recipe recipe = new Recipe("tarte a Tom", "test", List.of(), List.of(), List.of(), 1, 1, RecipeDifficulty.EASY);
    Recipe recipe2 = new Recipe("Tomme de Tom", "test2", List.of(), List.of(), List.of(), 1, 2, RecipeDifficulty.MEDIUM);
    Recipe recipe3 = new Recipe("crumble de Tom", "test3", List.of(), List.of(), List.of(), 1, 3, RecipeDifficulty.EASY);

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
//
        recipeList.getChildren().add(new RecipeCard(recipe));
    }

}
