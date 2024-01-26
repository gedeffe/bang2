package com.supinfo.recipe.step.panels;

import com.supinfo.recipe.recipe.Recipe;
import com.supinfo.recipe.step.Step;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStepPanel extends JPanel {
    private final JTextArea stepTextArea;

    public AddStepPanel(Recipe recipe) {
        super(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Add a step");
        this.setBorder(blackline);

        JPanel insidePanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Ajout de "Step : "
        gbc.gridx = 0;
        gbc.gridy = 0;
        insidePanel.add(new JLabel("Step : "), gbc);

        // Ajout zone de texte pour entrer la description de la step
        this.stepTextArea = new JTextArea(5, 20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        insidePanel.add(this.stepTextArea, gbc);


        // bouton "Add Tool"
        JButton addButton = new JButton("Add Step");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Step step = new Step(stepTextArea.getText());
                recipe.addStep(step);
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(addButton, BorderLayout.EAST);

        this.add(insidePanel, BorderLayout.CENTER);
    }
}
