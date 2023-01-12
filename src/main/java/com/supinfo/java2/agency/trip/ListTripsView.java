package com.supinfo.java2.agency.trip;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class ListTripsView extends JPanel {
    private final JScrollPane listTripsScrollPane;
    private final JTable listTripsTable;

    public ListTripsView(TableModel tableModel) {
        super(new BorderLayout());

        this.listTripsTable = new JTable(tableModel);
        this.listTripsScrollPane = new JScrollPane(this.listTripsTable);
        this.add(this.listTripsScrollPane, BorderLayout.CENTER);
    }

}
