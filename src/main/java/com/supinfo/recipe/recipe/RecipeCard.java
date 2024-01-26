package com.supinfo.recipe.recipe;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RecipeCard extends Pane {

    private final Recipe recipe;

        public RecipeCard(Recipe recipe) {
            super();
            this.recipe = recipe;

            Label recipeName = new Label(recipe.getName());
            this.getChildren().add(recipeName);

        }

}


