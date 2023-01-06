package com.supinfo.java2.arraysymbols;

import java.util.List;

public class ListSymbols {

    public List<List<String>> createPairLineTable(List<List<String>> tabs) {
        for (List<String> rowTab : tabs) {
            if (tabs.indexOf(rowTab) % 2 == 0) {
                for (int column = 0; column < rowTab.size(); column++) {
                    rowTab.set(column, "*");
                }
            } else {
                for (int column = 0; column < rowTab.size(); column++) {
                    rowTab.set(column, " ");
                }
            }
        }
        return tabs;
    }

    public List<List<String>> createPairColumnTable(List<List<String>> tabs) {
        for (List<String> rowTab : tabs) {
            for (int column = 0; column < rowTab.size(); column++) {
                if (column % 2 == 0) {
                    rowTab.set(column, "*");
                } else {
                    rowTab.set(column, " ");
                }
            }
        }
        return tabs;
    }

    public List<List<String>> createCarreColumnTable(List<List<String>> tabs) {
        for (List<String> rowTab : tabs) {
            int row = tabs.indexOf(rowTab);
            if (row == 0 || row == tabs.size() - 1) {
                for (int column = 0; column < rowTab.size(); column++) {
                    rowTab.set(column, "*");
                }
            } else {
                for (int column = 0; column < rowTab.size(); column++) {
                    if (column == 0 || column == rowTab.size() - 1) {
                        rowTab.set(column, "*");
                    } else {
                        rowTab.set(column, " ");
                    }
                }
            }
        }
        return tabs;
    }

    public List<List<String>> createCross(List<List<String>> tabs) {
        this.createCarreColumnTable(tabs);
        for (List<String> rowTab : tabs) {
            int row = tabs.indexOf(rowTab);
            for (int column = 0; column < rowTab.size(); column++) {
                if (column == row || column == rowTab.size() - row - 1) {
                    rowTab.set(column, "*");
                }
            }

        }
        return tabs;
    }

}
