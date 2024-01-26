package com.supinfo.recipe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeApplication.class.getResource("recipe-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Recipe Application");
        stage.setScene(scene);
        stage.show();
    }
}