package com.supinfo.java.day5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * EXERCISE 3 : Data Analysis
 * <p>
 * 3.1 Retrieve data from the US population csv file, named US-census
 * 3.2 Analyze data by sex, age
 * 3.3 Give the breakdowns by age group
 * 3.4 How many people are over 90 years old, under 10 years old
 * 3.5 Build bar charts by gender / age
 */
public class MainExo3 extends Application {

    public static void main(final String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // read data
        try (final InputStream csvFileInputStream = MainExo3.class.getResourceAsStream("/Us-Census.csv")) {
            final CsvDataReader csvDataReader = new CsvDataReader();
            final List<UsPeopleBean> beans = csvDataReader.readCsvFile(Objects.requireNonNull(csvFileInputStream));
            System.out.println("There is " + beans.size() + " answers.");
            // Give the breakdowns by age group
            BreakdownChartBuilder breakdownChartBuilder = new BreakdownChartBuilder();
            VBox breakdownsChart = breakdownChartBuilder.buildChart(beans);
            // Build bar charts by gender / age
            GenderAgeChartBuilder genderAgeChartBuilder = new GenderAgeChartBuilder();
            VBox genderAgeChart = genderAgeChartBuilder.buildChart(beans);

            VBox vbox = new VBox();
            vbox.getChildren().addAll(breakdownsChart, genderAgeChart);
            Scene scene = new Scene(vbox, 1024, 800);
            stage.setScene(scene);
            stage.show();
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
