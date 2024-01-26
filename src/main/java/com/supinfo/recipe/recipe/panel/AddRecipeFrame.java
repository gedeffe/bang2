package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.ingredient.IngredientModel;
import com.supinfo.recipe.ingredient.panels.AddIngredientPanel;
import com.supinfo.recipe.recipe.RecipeModel;
import com.supinfo.recipe.tools.AddToolPanel;
import com.supinfo.recipe.tools.ToolModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddRecipeFrame extends JFrame {
    private final AddRecipePanel addRecipePanel = new AddRecipePanel(new RecipeModel(new ArrayList<>()));
    private final AddIngredientPanel addIngredientPanel = new AddIngredientPanel(new IngredientModel(new ArrayList<>()));

    private final AddToolPanel addToolPanel = new AddToolPanel(new ToolModel(new ArrayList<>()));

    AddRecipeFrame() {
        super();

        this.setTitle("Add Recipe Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));

        this.add(this.addRecipePanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;

        southPanel.add(this.addIngredientPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        southPanel.add(this.addToolPanel, gbc);

        this.add(southPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        AddRecipeFrame frame = new AddRecipeFrame();
    }
}
