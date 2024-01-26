package com.supinfo.recipe.step;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StepTableModel extends AbstractTableModel {
    private final List<Step> steps = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return steps.size();
    }


    @Override
    public String getColumnName(int col) {

        return switch (col) {
            case 0 -> "Description";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int row, int col) {
        Step step = steps.get(row);
        return switch (col) {
            case 0 -> step.getDescription();
            default -> "";
        };
    }

    public void addStep(Step trip) {
        this.steps.add(trip);

        fireTableDataChanged();
    }
}
