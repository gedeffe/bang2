package com.supinfo.java2.arraysymbols;

import java.util.ArrayList;
import java.util.List;

public class ListTabs {

    public List<List<String>> init(int columns, int rows) {
        List<List<String>> tabs = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<String> rowTab = new ArrayList<>(columns);
            tabs.add(rowTab);
            for (int j = 0; j < columns; j++) {
                rowTab.add("");
            }
        }
        return tabs;
    }

    public void displayTabs(List<List<String>> tabs) {
        for (List<String> rowTab : tabs) {
            for (String value : rowTab) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
}
