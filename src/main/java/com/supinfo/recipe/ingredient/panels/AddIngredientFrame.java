package com.supinfo.recipe.ingredient.panels;

import com.supinfo.recipe.ingredient.Ingredient;
import com.supinfo.recipe.ingredient.IngredientModel;
import com.supinfo.recipe.ingredient.MeasureUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddIngredientFrame extends JFrame {
        private JTextField ingredientNameTextField;
        private JSpinner spinnerQuantity;

        private SpinnerModel spinnerModelQuantity;
        private JComboBox<MeasureUnit> unitComboBox;

        private IngredientModel ingredientModel;

        public AddIngredientFrame(IngredientModel ingredientModel) {
            super();

            this.ingredientModel = ingredientModel;

            setTitle("Add Ingredient");
            setSize(400, 220);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Ajout de "Ingredient : "
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Ingredient : "), gbc);

            // Ajout zone de texte pour entrer le nom ingredient
            ingredientNameTextField = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(ingredientNameTextField, gbc);

            // Ajout de "Quantite : "
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Quantite : "), gbc);

            // Ajout zone de texte pour entrer la quantite
            this.spinnerModelQuantity = new SpinnerNumberModel(0,0,1000,1);
            this.spinnerQuantity = new JSpinner(this.spinnerModelQuantity);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(this.spinnerQuantity, gbc);

            // Ajout de "Mesure : "
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Mesure : "), gbc);

            // Ajout zone de texte pour entrer la mesure
            DefaultComboBoxModel<MeasureUnit> destinationComboBox2model = new DefaultComboBoxModel<MeasureUnit>();
            destinationComboBox2model.addAll(List.of(MeasureUnit.values()));
            this.unitComboBox = new JComboBox<>(destinationComboBox2model);

            // Ajout combobox au panneau
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(this.unitComboBox, gbc);


            // bouton "Add Ingredient"
            JButton addButton = new JButton("Add Ingredient");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // use Ingredient model to create tool and store it
                    String name = ingredientNameTextField.getText();
                    double quantity = (double)spinnerModelQuantity.getValue();
                    MeasureUnit unit = (MeasureUnit)unitComboBox.getSelectedItem();

                    ingredientModel.addIngredient(new Ingredient(name, quantity, unit));
                }
            });

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.insets = new Insets(10, 10, 10, 10);
            panel.add(addButton, gbc);

            // Ajout bouton "Cancel"
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.insets = new Insets(10, 10, 10, 10);
            panel.add(cancelButton, gbc);

            add(panel);

            setVisible(true);
        }

        public static void main(String[] args) {
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            IngredientModel ingredientModel = new IngredientModel(ingredients);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new AddIngredientFrame(ingredientModel);
                }
            });
        }
    }

