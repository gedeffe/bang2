package com.supinfo.recipe.ingredient.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class addIngredientFrame extends JFrame {
        private JTextField placeTextField;
        private JTextField placeTextField1;
        private JTextField placeTextField2;


        public addIngredientFrame() {
            super();
            setTitle("Add Ingredient");
            setSize(400, 200);
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
            placeTextField1 = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(placeTextField1, gbc);

            // Ajout de "Mesure : "
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(10, 0, 0, 10);
            panel.add(new JLabel("Mesure : "), gbc);

            // Ajout zone de texte pour entrer la mesure
            placeTextField2 = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 0, 10);
            panel.add(placeTextField2, gbc);

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

