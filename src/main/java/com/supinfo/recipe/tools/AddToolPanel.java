package com.supinfo.recipe.tools;

import com.supinfo.recipe.ingredient.MeasureUnit;
import com.supinfo.recipe.recipe.Recipe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddToolPanel extends JPanel {
    private final JTextField toolNameTextField;
    private JSpinner spinner1;
    private JComboBox<MeasureUnit> destinationComboBox2;

    public AddToolPanel(Recipe recipe, ToolTableModel toolTableModel) {
        super(new BorderLayout());

        Border blackline = BorderFactory.createTitledBorder("Add a tool");
        this.setBorder(blackline);

        JPanel insidePanel = new JPanel(new GridBagLayout());

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
                String toolName = toolNameTextField.getText();
                Tool tool = new Tool(toolName);
                recipe.addTool(tool);

                toolTableModel.addTool(tool);
            }
        });

        this.add(addButton, BorderLayout.EAST);

        this.add(insidePanel, BorderLayout.CENTER);
    }
}
