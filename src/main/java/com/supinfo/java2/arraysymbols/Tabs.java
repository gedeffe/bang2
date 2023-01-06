package com.supinfo.java2.arraysymbols;

public class Tabs {

    public String[][] init(int columns, int rows) {
        return new String[rows][columns];
    }

    public void displayTabs(String[][] tabs) {
        for (int row = 0; row < tabs.length; row++) {
            String[] rowTab = tabs[row];
            for (int column = 0; column < rowTab.length; column++) {
                System.out.print(rowTab[column]);
            } // Arrays.toString(rowTab);
            System.out.println();
        }
    }
}
