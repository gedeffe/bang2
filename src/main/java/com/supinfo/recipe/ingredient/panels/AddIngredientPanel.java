package com.supinfo.recipe.ingredient.panels;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.ingredient.IngredientTableModel;
import com.supinfo.recipe.ingredient.MeasureUnit;
import com.supinfo.recipe.recipe.Recipe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddIngredientPanel extends JPanel {
    private JTextField ingredientNameTextField;
    private JSpinner spinnerQuantity;
    private SpinnerModel spinnerModelQuantity;
    private JComboBox<MeasureUnit> unitComboBox;

    public AddIngredientPanel(Recipe recipe, IngredientTableModel ingredientTableModel) {
        super(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Add an ingredient");
        this.setBorder(blackline);

        JPanel insidePanel = CreateInsidePanel();


        // bouton "Add Ingredient"
        JButton addButton = new JButton("Add Ingredient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // use Ingredient model to create tool and store it
                String name = ingredientNameTextField.getText();
                double quantity = (double) spinnerModelQuantity.getValue();
                MeasureUnit unit = (MeasureUnit) unitComboBox.getSelectedItem();

                Ingredient ingredient = new Ingredient(name, quantity, unit);
                recipe.addIngredient(ingredient);

                ingredientTableModel.addIngredient(ingredient);
            }
        });

        this.add(addButton, BorderLayout.EAST);

        this.add(insidePanel, BorderLayout.CENTER);


//            setVisible(true);
    }

    private JPanel CreateInsidePanel() {
        JPanel insidePanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Ajout de "Ingredient : "
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        insidePanel.add(new JLabel("Ingredient : "), gbc);

        // Ajout zone de texte pour entrer le nom ingredient
        ingredientNameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        insidePanel.add(ingredientNameTextField, gbc);

        // Ajout de "Quantite : "
        gbc.gridx = 0;
        gbc.gridy = 1;
        insidePanel.add(new JLabel("Quantite : "), gbc);

        // Ajout zone de texte pour entrer la quantite
        this.spinnerModelQuantity = new SpinnerNumberModel(0, 0, 1000, 1.0);
        this.spinnerQuantity = new JSpinner(this.spinnerModelQuantity);
        gbc.gridx = 1;
        gbc.gridy = 1;
        insidePanel.add(this.spinnerQuantity, gbc);

        // Ajout de "Mesure : "
        gbc.gridx = 0;
        gbc.gridy = 2;
        insidePanel.add(new JLabel("Mesure : "), gbc);

        // Ajout zone de texte pour entrer la mesure
        DefaultComboBoxModel<MeasureUnit> destinationComboBox2model = new DefaultComboBoxModel<>();
        destinationComboBox2model.addAll(List.of(MeasureUnit.values()));
        this.unitComboBox = new JComboBox<>(destinationComboBox2model);

        // Ajout combobox au panneau
        gbc.gridx = 1;
        gbc.gridy = 2;
        insidePanel.add(this.unitComboBox, gbc);

        return insidePanel;
    }
}

