package com.supinfo.recipe.tools;

import com.supinfo.recipe.ingredient.MeasureUnit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToolPanel extends JPanel {
    private JTextField toolNameTextField;
    private JSpinner spinner1;
    private JComboBox<MeasureUnit> destinationComboBox2;

    private ToolModel toolModel;

    public AddToolPanel(ToolModel toolModel) {
        super(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Add a tool");
        this.setBorder(blackline);

        JPanel insidePanel = new JPanel(new GridBagLayout());

        this.toolModel = toolModel;

        GridBagConstraints gbc = new GridBagConstraints();

        // Ajout de "Tool : "
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 10);
        insidePanel.add(new JLabel("Tool : "), gbc);

        // Ajout zone de texte pour entrer le nom du tool
        toolNameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 0, 10);
        insidePanel.add(toolNameTextField, gbc);


        // bouton "Add Tool"
        JButton addButton = new JButton("Add Tool");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create tool and add it to the model
                String toolName = toolNameTextField.getText();
                toolModel.addTool(new Tool(toolName));
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(addButton, BorderLayout.EAST);

        this.add(insidePanel, BorderLayout.CENTER);
    }
}
