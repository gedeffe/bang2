package com.supinfo.java2.swing;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class ListPlacesView {
    private final JScrollPane listPlacesScrollPane;
    private final JTable listPlacesTable;
    @Getter
    private final JPanel rootPanel;

    public ListPlacesView(TableModel tableModel) {
        this.rootPanel = new JPanel(new BorderLayout());

        this.listPlacesTable = new JTable(tableModel);
        this.listPlacesScrollPane = new JScrollPane(this.listPlacesTable);
        this.rootPanel.add(this.listPlacesScrollPane, BorderLayout.CENTER);
    }

}
