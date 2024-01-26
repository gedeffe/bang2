package com.supinfo.recipe.tools;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ToolTableModel extends AbstractTableModel {
    private final List<Tool> tools = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return tools.size();
    }


    @Override
    public String getColumnName(int col) {

        return switch (col) {
            case 0 -> "Name";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Tool tool = tools.get(row);
        return switch (col) {
            case 0 -> tool.getName();
            default -> "";
        };
    }

    public void addTrip(Tool trip) {
        this.tools.add(trip);

        fireTableDataChanged();
    }
}
