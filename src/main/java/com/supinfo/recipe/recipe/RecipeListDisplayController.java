package com.supinfo.recipe.recipe;

import com.supinfo.recipe.recipe.panel.AddRecipeFrame;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import com.supinfo.recipe.recipe.event.RecipeEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class RecipeListDisplayController implements RecipeEventListener {

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
        this.recipeModel.subscribe(this);
    }

    @FXML
    protected void handleAddRecipeButtonAction() {
        Dialog<Recipe> dialog = new Dialog<>();

        dialog.setTitle("Add Recipe");


        ButtonType addButtonType = new ButtonType("Add Recipe", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(addButtonType);

        final Button btOk = (Button) dialog.getDialogPane().lookupButton(addButtonType);


        SwingNode swingNode = new SwingNode();
        dialog.getDialogPane().setExpandableContent(swingNode);
        // opening the add recipe frame
        createSwingContent(swingNode, btOk);
        dialog.showAndWait();

    }

    @Override
    public void onCreated(Recipe recipe) {
        recipeList.getChildren().add(new RecipeCard(recipe));
    }

    @Override
    public void onDeleted(Recipe recipe) {
        recipeList.getChildren().removeIf(node -> ((RecipeCard) node).getRecipe() == recipe);
    }

    private void createSwingContent(final SwingNode swingNode, Button button) {
        SwingUtilities.invokeLater(() -> {
            AddRecipeFrame addRecipeFrame = new AddRecipeFrame();
            swingNode.setContent(addRecipeFrame);
            button.addEventFilter(ActionEvent.ACTION, event -> {
                Recipe recipe = addRecipeFrame.getRecipe();
                recipeModel.addRecipe(recipe);
            });
        });
    }

}
