package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.recipe.RecipeDifficulty;
import com.supinfo.recipe.recipe.RecipeModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddRecipePanel extends JPanel {
    private JButton addButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel personNumberLabel;
    private JSpinner personNumberField;
    private JLabel durationLabel;
    private JSpinner durationField;
    private JLabel difficultyLabel;
    private JComboBox<RecipeDifficulty> difficultyField;
    private JPanel fieldsPanel;
    private final RecipeModel recipeModel;

    public AddRecipePanel(RecipeModel recipeModel) {
        super();
        this.recipeModel = recipeModel;
        this.fieldsPanel = new JPanel(new SpringLayout());

        this.addButton = new JButton("Add Recipe"); // naming here is redundant because action defines name

        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipeModel.addRecipe(new Recipe(
                        nameField.getText(),
                        descriptionField.getText(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        (int) personNumberField.getValue(),
                        (int) durationField.getValue(),
                        null)
                );
            }
        };

        action.putValue(AbstractAction.NAME, "Add Recipe");
        this.addButton.setAction(action);

        this.nameLabel = new JLabel("Recipe Name");
        this.nameField = new JTextField(20);
        this.nameField.setToolTipText("Text field to specify the recipe's name");

        this.descriptionLabel = new JLabel("Recipe Description");
        this.descriptionField = new JTextField(20);
        this.descriptionField.setToolTipText("Text area to specify the recipe's description");

        this.personNumberLabel = new JLabel("Number of person");
        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 1000, 0.1);
        this.personNumberField = new JSpinner(spinnerModel);

        this.durationLabel = new JLabel("Duration");
        this.durationField = new JSpinner(spinnerModel);

        this.difficultyLabel = new JLabel("Difficulty");
        DefaultComboBoxModel<RecipeDifficulty> difficultyModel = new DefaultComboBoxModel<RecipeDifficulty>();
        difficultyModel.addAll(List.of(RecipeDifficulty.values()));
        this.difficultyField = new JComboBox<>(difficultyModel);

        this.fieldsPanel.add(this.nameLabel);
        this.fieldsPanel.add(this.nameField);

        this.fieldsPanel.add(this.descriptionLabel);
        this.fieldsPanel.add(this.descriptionField);

        this.fieldsPanel.add(this.personNumberLabel);
        this.fieldsPanel.add(this.personNumberField);

        this.fieldsPanel.add(this.durationLabel);
        this.fieldsPanel.add(this.durationField);

        this.fieldsPanel.add(this.difficultyLabel);
        this.fieldsPanel.add(this.difficultyField);

        this.add(this.fieldsPanel);
        this.add(this.addButton);

        AddRecipePanel.makeCompactGrid(this.fieldsPanel,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
    }

    // From https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringGridProject/src/layout/SpringUtilities.java
    public static void makeCompactGrid(Container parent,
                                       int rows, int cols,
                                       int initialX, int initialY,
                                       int xPad, int yPad) {
        SpringLayout layout;
        try {
            layout = (SpringLayout)parent.getLayout();
        } catch (ClassCastException exc) {
            System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
            return;
        }

        //Align all cells in each column and make them the same width.
        Spring x = Spring.constant(initialX);
        for (int c = 0; c < cols; c++) {
            Spring width = Spring.constant(0);
            for (int r = 0; r < rows; r++) {
                width = Spring.max(width,
                        getConstraintsForCell(r, c, parent, cols).
                                getWidth());
            }
            for (int r = 0; r < rows; r++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setX(x);
                constraints.setWidth(width);
            }
            x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
        }

        //Align all cells in each row and make them the same height.
        Spring y = Spring.constant(initialY);
        for (int r = 0; r < rows; r++) {
            Spring height = Spring.constant(0);
            for (int c = 0; c < cols; c++) {
                height = Spring.max(height,
                        getConstraintsForCell(r, c, parent, cols).
                                getHeight());
            }
            for (int c = 0; c < cols; c++) {
                SpringLayout.Constraints constraints =
                        getConstraintsForCell(r, c, parent, cols);
                constraints.setY(y);
                constraints.setHeight(height);
            }
            y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
        }

        //Set the parent's size.
        SpringLayout.Constraints pCons = layout.getConstraints(parent);
        pCons.setConstraint(SpringLayout.SOUTH, y);
        pCons.setConstraint(SpringLayout.EAST, x);
    }

    private static SpringLayout.Constraints getConstraintsForCell(
            int row, int col,
            Container parent,
            int cols) {
        SpringLayout layout = (SpringLayout) parent.getLayout();
        Component c = parent.getComponent(row * cols + col);
        return layout.getConstraints(c);
    }
}
