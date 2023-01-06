package com.supinfo.java2.arraysymbols;

import java.util.ArrayList;
import java.util.List;

public class Symbols {

    public String[][] createPairLineTable(String[][] tabs) {
        for (int row = 0; row < tabs.length; row++) {
            String[] rowTab = tabs[row];
            if (row % 2 == 0) {
                for (int column = 0; column < rowTab.length; column++) {
                    rowTab[column] = "*";
                }
            } else {
                for (int column = 0; column < rowTab.length; column++) {
                    rowTab[column] = " ";
                }
            }
        }
        return tabs;
    }

    public String[][] createPairColumnTable(String[][] tabs) {
        for (int row = 0; row < tabs.length; row++) {
            String[] rowTab = tabs[row];
            for (int column = 0; column < rowTab.length; column++) {
                if (column % 2 == 0) {
                    rowTab[column] = "*";
                } else {
                    rowTab[column] = " ";
                }
            }
        }
        return tabs;
    }

    public String[][] createCarreColumnTable(String[][] tabs) {
        for (int row = 0; row < tabs.length; row++) {
            String[] rowTab = tabs[row];
            if (row == 0 || row == tabs.length - 1) {
                for (int column = 0; column < rowTab.length; column++) {
                    rowTab[column] = "*";
                }
            } else {
                for (int column = 0; column < rowTab.length; column++) {
                    if (column == 0 || column == rowTab.length - 1) {
                        rowTab[column] = "*";
                    } else {
                        rowTab[column] = " ";
                    }
                }
            }
        }
        return tabs;
    }

    public String[][] createCross(String[][] tabs) {
        this.createCarreColumnTable(tabs);
        for (int row = 0; row < tabs.length; row++) {
            String[] rowTab = tabs[row];
            for (int column = 0; column < rowTab.length; column++) {
                if (column == row || column == rowTab.length - row - 1) {
                    rowTab[column] = "*";
                }
            }

        }
        return tabs;
    }

    public void testCollection() {
        List<String> test = new ArrayList<>();

        test.toArray();

    }


}
