package com.supinfo.java2.arraysymbols;

public class Main {

    public static void main(String[] args) {
        Tabs tabs = new Tabs();
        Symbols symbols = new Symbols();

        String[][] init = tabs.init(10, 20);

        String[][] pairLineTable = symbols.createPairLineTable(init);
        tabs.displayTabs(pairLineTable);

        String[][] pairColumnTable = symbols.createPairColumnTable(init);
        tabs.displayTabs(pairColumnTable);

        String[][] carreColumnTable = symbols.createCarreColumnTable(init);
        tabs.displayTabs(carreColumnTable);

        String[][] cross = symbols.createCross(init);
        tabs.displayTabs(cross);
    }
}
