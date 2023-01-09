package com.supinfo.java2.arraysymbols;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListTabs {

    public List<List<String>> retrieveUserTabs() {
        List<List<String>> init;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        int rows;

        try {
            System.out.println("Enter row size:");
            rows = Integer.parseInt(scanner.nextLine());  // Read user input
            init = this.init(rows, rows);
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            System.out.println("We expect a number, please try again.");
            init = this.retrieveUserTabs();
        } catch (IllegalArgumentException iae) {
            System.out.println("Enter value less than 50, please.");
            init = this.retrieveUserTabs();
        } catch (Exception exception) {
            System.out.println("Enter positive value, please.");
            init = this.retrieveUserTabs();
        }
        return init;
    }

    public List<List<String>> init(int columns, int rows) {
        if (rows > 50) {
            throw new IllegalArgumentException("Value is too big !");
        }

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
