package com.supinfo.java.day5;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.List;

public class BreakdownChartBuilder {
    public VBox buildChart(List<UsPeopleBean> beans) {
        // Series
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("US population");

        long countChildren = beans.stream() //
                .filter(people -> people.getAge() < 8) //
                .count();
        dataSeries.getData().add(new XYChart.Data<>("Child (0-7 years)", countChildren));
        long countYoungAdults = beans.stream() //
                .filter(people -> people.getAge() > 7 && people.getAge() < 18) //
                .count();
        dataSeries.getData().add(new XYChart.Data<>("Young adults (8-18 years)", countYoungAdults));
        long countMiddleAgedAdults = beans.stream() //
                .filter(people -> people.getAge() > 18 && people.getAge() < 50) //
                .count();
        dataSeries.getData().add(new XYChart.Data<>("Middle-aged adults (19-49 years)", countMiddleAgedAdults));
        long countOldAdults = beans.stream() //
                .filter(people -> people.getAge() > 49) //
                .count();
        dataSeries.getData().add(new XYChart.Data<>("Old adults (50 years and above)", countOldAdults));

        // build chart to display breakdowns by age group
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Age group");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of people");

        // Create a BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        // Add Series to BarChart.
        barChart.getData().add(dataSeries);

        barChart.setTitle("Breakdowns by age group");

        return new VBox(barChart);
    }
}
