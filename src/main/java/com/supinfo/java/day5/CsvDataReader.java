package com.supinfo.java.day5;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * To be able to retrieve data from a CSV file.
 */
public class CsvDataReader {

    public List<UsPeopleBean> readCsvFile(final InputStream csvFile) {
        final List<UsPeopleBean> beans = new ArrayList<>();
        try (final InputStreamReader fileReader = new InputStreamReader(csvFile)) {
            StringBuilder fileContent = new StringBuilder();
            try (BufferedReader realFileReader = new BufferedReader(fileReader)) {
                realFileReader.lines() //
                        .map(line -> line.replace("\"", "")) // remove " character
                        .forEach(line -> fileContent.append(line).append("\n"));
            }
            Reader reader = new StringReader(fileContent.toString());
            beans.addAll(new CsvToBeanBuilder<UsPeopleBean>(reader)
                    .withType(UsPeopleBean.class).withIgnoreLeadingWhiteSpace(true).withSeparator(',').build().parse());
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
        return beans;
    }
}
