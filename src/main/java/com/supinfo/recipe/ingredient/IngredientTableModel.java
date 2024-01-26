package com.supinfo.recipe.ingredient;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class IngredientTableModel extends AbstractTableModel {
    private final List<Ingredient> ingredients = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return ingredients.size();
    }


    @Override
    public String getColumnName(int col) {

        return switch (col) {
            case 0 -> "Name";
            case 1 -> "Quantity";
            case 2 -> "Unit";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Ingredient ingredient = ingredients.get(row);
        return switch (col) {
            case 0 -> ingredient.getName();
            case 1 -> ingredient.getQuantity();
            case 2 -> ingredient.getUnit();
            default -> "";
        };
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);

        fireTableDataChanged();
    }
}
