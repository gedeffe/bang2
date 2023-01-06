package com.supinfo.java2.arraysymbols;

import java.util.List;

public class ListMain {

    public static void main(String[] args) {
        ListTabs tabs = new ListTabs();
        ListSymbols symbols = new ListSymbols();

        List<List<String>> init = tabs.init(10, 20);

        List<List<String>> pairLineTable = symbols.createPairLineTable(init);
        tabs.displayTabs(pairLineTable);

        List<List<String>> pairColumnTable = symbols.createPairColumnTable(init);
        tabs.displayTabs(pairColumnTable);

        List<List<String>> carreColumnTable = symbols.createCarreColumnTable(init);
        tabs.displayTabs(carreColumnTable);

        List<List<String>> cross = symbols.createCross(init);
        tabs.displayTabs(cross);
    }
}
