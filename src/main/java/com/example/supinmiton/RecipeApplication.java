package com.example.supinmiton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RecipeApplication.class.getResource("recipe-view.fxml"));

            ScrollPane scrollPane = new ScrollPane(fxmlLoader.load());

            Scene scene = new Scene(scrollPane, 410, 750);
            stage.setTitle("Apple Pie");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}