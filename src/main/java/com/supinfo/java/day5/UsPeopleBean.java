package com.supinfo.java.day5;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

/**
 * To store data retrieved from CSV file.
 */
@Getter
public class UsPeopleBean {

    @CsvBindByPosition(position = 0)
    private String answerDate; // when we have picked up the information
    @CsvBindByPosition(position = 1)
    private int age; // age of people when we question them
    @CsvBindByPosition(position = 2)
    private String gender; // M or F
    @CsvBindByPosition(position = 3)
    private String identifier; // a number to identify one people anonymously

    @Override
    public String toString() {
        return "UsPeopleBean{" +
                "answerDate='" + this.answerDate + '\'' +
                ", age='" + this.age + '\'' +
                ", gender='" + this.gender + '\'' +
                ", identifier='" + this.identifier + '\'' +
                '}';
    }
}
