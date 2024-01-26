package com.supinfo.recipe.recipe.panel;

import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.recipe.RecipeDifficulty;
import com.supinfo.recipe.recipe.RecipeModel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class AddRecipePanel extends JPanel {
    private final JButton addButton;
    private final JTextField nameField;
    private final JTextField descriptionField;
    private final JSpinner personNumberField;
    private final JSpinner durationField;
    private final JComboBox<RecipeDifficulty> difficultyField;
    private final JPanel fieldsPanel;
    private final UUID recipeUUID;

    // Models
    private final RecipeModel recipeModel;

    public AddRecipePanel(UUID recipeUUID, RecipeModel recipeModel) {
        super();
        this.recipeUUID = recipeUUID;
        this.recipeModel = recipeModel;
        this.fieldsPanel = new JPanel(new SpringLayout());

        // validate button
        this.addButton = new JButton("Add Recipe"); // naming here is redundant because action defines name

        AbstractAction action = getAbstractAction(recipeModel);

        this.addButton.setAction(action);

        // Recipe name
        JLabel nameLabel = new JLabel("Recipe Name");
        this.nameField = new JTextField(20);
        this.nameField.setToolTipText("Text field to specify the recipe's name");

        this.fieldsPanel.add(nameLabel);
        this.fieldsPanel.add(this.nameField);

        // Recipe description
        JLabel descriptionLabel = new JLabel("Recipe Description");
        this.descriptionField = new JTextField(20);
        this.descriptionField.setToolTipText("Text area to specify the recipe's description");

        this.fieldsPanel.add(descriptionLabel);
        this.fieldsPanel.add(this.descriptionField);

        // Recipe person number
        JLabel personNumberLabel = new JLabel("Number of person");
        SpinnerModel spPersonNumber = new SpinnerNumberModel(0, 0, 100, 1);
        this.personNumberField = new JSpinner(spPersonNumber);

        this.fieldsPanel.add(personNumberLabel);
        this.fieldsPanel.add(this.personNumberField);

        // Recipe duration
        JLabel durationLabel = new JLabel("Duration");
        SpinnerModel spDuration = new SpinnerNumberModel(0, 0, 1000, 15);
        this.durationField = new JSpinner(spDuration);

        this.fieldsPanel.add(durationLabel);
        this.fieldsPanel.add(this.durationField);

        // Recipe difficulty
        JLabel difficultyLabel = new JLabel("Difficulty");
        DefaultComboBoxModel<RecipeDifficulty> difficultyModel = new DefaultComboBoxModel<>();
        difficultyModel.addAll(List.of(RecipeDifficulty.values()));
        this.difficultyField = new JComboBox<>(difficultyModel);

        this.fieldsPanel.add(difficultyLabel);
        this.fieldsPanel.add(this.difficultyField);


        // add fields panel
        this.add(this.fieldsPanel);

        // add the button
        this.add(this.addButton);

        AddRecipePanel.makeCompactGrid(this.fieldsPanel,
                5, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
    }

    private AbstractAction getAbstractAction(RecipeModel recipeModel) {
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
        return action;
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
