package com.supinfo.recipe.recipe.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddRecipePanel extends JPanel {
    private JButton addButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JTextField personNumberField;
    private JPanel fieldsPanel;

    public AddRecipePanel() {
        super();
        this.fieldsPanel = new JPanel(new SpringLayout());
        this.addButton = new JButton();
        this.addButton.setVerticalAlignment(AbstractButton.CENTER);
        this.addButton.setHorizontalAlignment(AbstractButton.LEADING);
        this.addButton.setPreferredSize(new Dimension(50,10));
        this.addButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.nameLabel = new JLabel("Recipe Name");
        this.nameField = new JTextField(20);
        this.nameField.setToolTipText("Text field to specify the recipe's name");

        this.descriptionLabel = new JLabel("Recipe Description");
        this.descriptionField = new JTextField(20);
        this.descriptionField.setToolTipText("Text area to specify the recipe's description");

        this.fieldsPanel.add(this.nameLabel);
        this.fieldsPanel.add(this.nameField);
        this.fieldsPanel.add(this.descriptionLabel);
        this.fieldsPanel.add(this.descriptionField);
        this.add(this.fieldsPanel);
        this.add(this.addButton);

        AddRecipePanel.makeCompactGrid(this.fieldsPanel,
                2, 2, //rows, cols
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
