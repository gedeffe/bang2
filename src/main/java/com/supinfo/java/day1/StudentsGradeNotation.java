package com.supinfo.java.day1;

public class StudentsGradeNotation {

    public String evaluateStudent(int average) {

        return switch (average) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> "Postponed";
            case 10, 11 -> "Fair";
            case 12, 13 -> "Fairly good";
            case 14, 15 -> "Good";
            case 16, 17 -> "Very good";
            case 18, 19, 20 -> "Excellent";
            default -> throw new IllegalArgumentException("wrong average value = " + average);
        };
    }
}
