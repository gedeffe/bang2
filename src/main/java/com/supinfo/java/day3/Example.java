package com.supinfo.java.day3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<String> listObj = new ArrayList<>();
        listObj.add("String1");
        listObj.add("String2");
        listObj.add(new Date().toString());

        for (String obj : listObj) {
            System.out.println(obj);
        }
        String[] myTab = new String[listObj.size()];
        String[] strings = listObj.toArray(myTab);
    }
}
