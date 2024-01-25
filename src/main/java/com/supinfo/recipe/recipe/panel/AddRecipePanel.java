package com.supinfo.recipe.recipe.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddRecipePanel extends JPanel {
    private JButton addButton;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JTextField personNumberField;

    public AddRecipePanel() {
        super(new FlowLayout(FlowLayout.CENTER));
        this.addButton = new JButton();
        this.addButton.setText("Add Recipe");
        this.addButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.nameField = new JTextField();
        this.nameField.setText("Recipe Name");
        this.nameField.setToolTipText("Text field to specify the recipe's name");

        this.descriptionField = new JTextArea();
        this.descriptionField.setText("Recipe Description");
        this.descriptionField.setToolTipText("Text area to specify the recipe's description");

        this.add(this.addButton);
        this.add(this.nameField);
        this.add(this.descriptionField);
    }
}
