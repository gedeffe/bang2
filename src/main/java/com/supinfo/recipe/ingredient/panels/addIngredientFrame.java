package com.supinfo.recipe.ingredient.panels;

import com.supinfo.recipe.ingredient.MeasureUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class addIngredientFrame extends JFrame {
        private JTextField placeTextField;
        private JSpinner spinner1;
        private JComboBox<MeasureUnit> destinationComboBox2;

        public addIngredientFrame() {
            super();
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
            placeTextField = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(placeTextField, gbc);

            // Ajout de "Quantite : "
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Quantite : "), gbc);

            // Ajout zone de texte pour entrer la quantite
            SpinnerNumberModel model = new SpinnerNumberModel(0,0,1000,1);
            spinner1 = new JSpinner(model);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(spinner1, gbc);

            // Ajout de "Mesure : "
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Mesure : "), gbc);

            // Ajout zone de texte pour entrer la mesure
            DefaultComboBoxModel<MeasureUnit> destinationComboBox2model = new DefaultComboBoxModel<MeasureUnit>();
            destinationComboBox2model.addAll(List.of(MeasureUnit.values()));
            destinationComboBox2 = new JComboBox<>(destinationComboBox2model);

            // Ajout combobox au panneau
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(destinationComboBox2, gbc);


            // bouton "Add Ingredient"
            JButton addButton = new JButton("Add Ingredient");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new addIngredientFrame();
                }
            });
        }
    }

