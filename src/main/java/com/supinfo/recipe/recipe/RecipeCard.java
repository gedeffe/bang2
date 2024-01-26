package com.supinfo.recipe.recipe;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RecipeCard extends Rectangle {

        public RecipeCard(Recipe recipe) {
            super(600, 135);
            setFill(Color.RED);
        }

}


