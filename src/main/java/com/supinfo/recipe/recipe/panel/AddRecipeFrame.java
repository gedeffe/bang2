package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.ingredient.IngredientTableModel;
import com.supinfo.recipe.ingredient.panels.AddIngredientPanel;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.StepTableModel;
import com.supinfo.recipe.step.panels.AddStepPanel;
import com.supinfo.recipe.tools.AddToolPanel;
import com.supinfo.recipe.tools.ToolTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddRecipeFrame extends JPanel {
    // UUID for the current recipe
    private final Recipe recipe = new Recipe();

    // Panels
    private final AddRecipePanel addRecipePanel = new AddRecipePanel(recipe);
    private final AddIngredientPanel addIngredientPanel = new AddIngredientPanel(recipe);
    private final AddToolPanel addToolPanel = new AddToolPanel(recipe);
    private final AddStepPanel addStepPanel = new AddStepPanel(recipe);

    private final JPanel centerPanel;

    // Table Models
    private ToolTableModel toolTableModel;
    private IngredientTableModel ingredientTableModel;
    private StepTableModel stepTableModel;


    public AddRecipeFrame() {
        super();

        this.setPreferredSize(new Dimension(500, 500));

        this.add(this.addRecipePanel, BorderLayout.NORTH);

        // Center panel (TableModel for tools, ingredient and steps)
        centerPanel = new JPanel(new GridBagLayout());


        // South Panel (add ingredient, tools and steps)
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
    }

    private JPanel createToolPanel() {
        JPanel toolsPanel = new JPanel(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Tools");
        toolsPanel.setBorder(blackline);

        JTable table = new JTable(this.toolTableModel);

        toolsPanel.add(new JScrollPane(table));

        return toolsPanel;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }
}
