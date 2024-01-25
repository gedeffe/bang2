package com.supinfo.recipe.recipe.panel;

import javax.swing.*;
import java.awt.*;

public class AddRecipeFrame extends JFrame {
    private final AddRecipePanel addRecipePanel = new AddRecipePanel();
    AddRecipeFrame() {
        this.setTitle("Add Recipe Frame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(this.addRecipePanel, BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(500,500));
        this.pack();
        this.setVisible(true);
    }
}
