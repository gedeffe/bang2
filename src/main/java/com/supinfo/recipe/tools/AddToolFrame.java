package com.supinfo.recipe.tools;

import com.supinfo.recipe.ingredient.MeasureUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddToolFrame extends JFrame {
    private JTextField toolNameTextField;
    private JSpinner spinner1;
    private JComboBox<MeasureUnit> destinationComboBox2;

    private ToolModel toolModel;

    public AddToolFrame(ToolModel toolModel) {
        super();

        this.toolModel = toolModel;

        setTitle("Add Tool");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ajout de "Tool : "
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 10);
        panel.add(new JLabel("Tool : "), gbc);

        // Ajout zone de texte pour entrer le nom du tool
        toolNameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 0, 10);
        panel.add(toolNameTextField, gbc);


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

        List<Tool> tools = new ArrayList<>();
        ToolModel toolModel = new ToolModel(tools);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddToolFrame(toolModel);
            }
        });
    }
}
