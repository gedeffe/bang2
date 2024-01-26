package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.recipe.RecipeModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddRecipeFrame extends JFrame {
    private final AddRecipePanel addRecipePanel = new AddRecipePanel(new RecipeModel());
    AddRecipeFrame() {
        this.setTitle("Add Recipe Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));

        this.add(this.addRecipePanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }
}
