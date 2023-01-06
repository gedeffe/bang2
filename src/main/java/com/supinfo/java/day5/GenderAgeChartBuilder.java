package com.supinfo.java.day5;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class GenderAgeChartBuilder {
    public VBox buildChart(List<UsPeopleBean> beans) {
        // Series
        XYChart.Series<String, Number> menDataSeries = new XYChart.Series<>();
        menDataSeries.setName("Men");
        XYChart.Series<String, Number> womenDataSeries = new XYChart.Series<>();
        womenDataSeries.setName("Women");

        // Use a set of different ages to initialize data series
        beans.stream() //
                .mapToInt(UsPeopleBean::getAge) // transform to stream of Integer
                .distinct() // we would like a set (not a list)
                .forEach(age -> this.populateSeriesForAge(age, beans, menDataSeries, womenDataSeries));


        // build chart to display breakdowns by age group
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Age (years)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of people");

        // Create a BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        // Add Series to BarChart.
        barChart.getData().add(womenDataSeries);
        barChart.getData().add(menDataSeries);

        barChart.setTitle("Charts by gender / age");

        return new VBox(barChart);
    }

    private void populateSeriesForAge(int age, List<UsPeopleBean> beans, XYChart.Series<String, Number> menDataSeries, XYChart.Series<String, Number> womenDataSeries) {
        this.populateSeriesForAge(age, beans, menDataSeries, "M");
        this.populateSeriesForAge(age, beans, womenDataSeries, "F");
    }

    private void populateSeriesForAge(int age, List<UsPeopleBean> beans, XYChart.Series<String, Number> dataSeries, String gender) {
        long count = beans.stream() //
                .filter(people -> people.getAge() == age) // filter on age predicate
                .map(UsPeopleBean::getGender) // transform current stream of people into stream of gender
                .filter(Objects::nonNull) // keep only values not null
                .map(String::trim) // remove whitespaces
                .filter(gender::equals) // filter on gender
                .count();
        dataSeries.getData().add(new XYChart.Data<>("Age " + age, count));
    }

}
