package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.ingredient.IngredientModel;
import com.supinfo.recipe.ingredient.panels.AddIngredientPanel;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.recipe.RecipeModel;
import com.supinfo.recipe.step.StepModel;
import com.supinfo.recipe.step.panels.AddStepPanel;
import com.supinfo.recipe.tools.AddToolPanel;
import com.supinfo.recipe.tools.ToolModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class AddRecipeFrame extends JFrame {
    // UUID for the current recipe
    private Recipe recipe = new Recipe();

    // Panels
    private final AddRecipePanel addRecipePanel = new AddRecipePanel(recipe);
    private final AddIngredientPanel addIngredientPanel = new AddIngredientPanel(recipe);
    private final AddToolPanel addToolPanel = new AddToolPanel(recipe);
    private final AddStepPanel addStepPanel = new AddStepPanel(recipe);

    AddRecipeFrame() {
        super();

        this.setTitle("Add a Recipe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));

        this.add(this.addRecipePanel, BorderLayout.NORTH);
        JPanel southPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Bottom panel for adding element in recipe
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        southPanel.add(this.addIngredientPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        southPanel.add(this.addToolPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        southPanel.add(this.addStepPanel, gbc);

        this.add(southPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        AddRecipeFrame frame = new AddRecipeFrame();
    }
}
