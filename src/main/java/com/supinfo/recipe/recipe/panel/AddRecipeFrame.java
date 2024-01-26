package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.ingredient.IngredientTableModel;
import com.supinfo.recipe.ingredient.panels.AddIngredientPanel;
import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.StepTableModel;
import com.supinfo.recipe.step.panels.AddStepPanel;
import com.supinfo.recipe.tools.AddToolPanel;
import com.supinfo.recipe.tools.ToolTableModel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AddRecipeFrame extends JPanel {
    private final JPanel centerPanel;
    // UUID for the current recipe
    @Getter
    private final Recipe recipe = new Recipe();
    // Panels
    private AddRecipePanel addRecipePanel;
    private AddIngredientPanel addIngredientPanel;
    private AddToolPanel addToolPanel;
    private AddStepPanel addStepPanel;
    // Table Models
    private ToolTableModel toolTableModel;
    private IngredientTableModel ingredientTableModel;
    private StepTableModel stepTableModel;


    public AddRecipeFrame() {
        super();

        // Init TableModels
        initTableModels();

        // init Panels
        initPanels();

        this.setPreferredSize(new Dimension(1500, 1000));

        this.add(this.addRecipePanel, BorderLayout.NORTH);

        // Center panel (TableModel for tools, ingredient and steps)
        centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.weightx = 1.0;

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        centerPanel.add(createIngredientsPanel(), gbc1);
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        centerPanel.add(createToolsPanel(), gbc1);
        gbc1.gridx = 2;
        gbc1.gridy = 0;
        centerPanel.add(createStepsPanel(), gbc1);

        this.add(centerPanel, BorderLayout.CENTER);


        // South Panel (add ingredient, tools and steps)
        JPanel southPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc2 = new GridBagConstraints();

        // Bottom panel for adding element in recipe
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        southPanel.add(this.addIngredientPanel, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 0;
        southPanel.add(this.addToolPanel, gbc2);

        gbc2.gridx = 2;
        gbc2.gridy = 0;
        southPanel.add(this.addStepPanel, gbc2);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void initPanels() {
        this.addRecipePanel = new AddRecipePanel(recipe);
        this.addIngredientPanel = new AddIngredientPanel(recipe, this.ingredientTableModel);
        this.addToolPanel = new AddToolPanel(recipe, this.toolTableModel);
        this.addStepPanel = new AddStepPanel(recipe, this.stepTableModel);
    }

    private void initTableModels() {
        this.ingredientTableModel = new IngredientTableModel();
        this.toolTableModel = new ToolTableModel();
        this.stepTableModel = new StepTableModel();
    }

    private JPanel createToolsPanel() {
        JPanel toolsPanel = new JPanel(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Tools");
        toolsPanel.setBorder(blackline);

        JTable table = new JTable(this.toolTableModel);

        toolsPanel.add(new JScrollPane(table));

        return toolsPanel;
    }

    private JPanel createStepsPanel() {
        JPanel stepsPanel = new JPanel(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Steps");
        stepsPanel.setBorder(blackline);

        JTable table = new JTable(this.stepTableModel);

        stepsPanel.add(new JScrollPane(table));

        return stepsPanel;
    }

    private JPanel createIngredientsPanel() {
        JPanel ingredientsPanel = new JPanel(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Ingredients");
        ingredientsPanel.setBorder(blackline);

        JTable table = new JTable(this.ingredientTableModel);

        ingredientsPanel.add(new JScrollPane(table));

        return ingredientsPanel;
    }

}
