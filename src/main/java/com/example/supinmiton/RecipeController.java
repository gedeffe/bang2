package com.example.supinmiton;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.example.supinmiton.DetailsApplication.*;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeController {

    @FXML
    private Button openDetailsButton;

    @FXML
    public void initialize(){
        openDetailsButton.setOnAction(event -> {
            try {
                openDetails();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void openDetails() throws IOException {
        System.out.println("ouais");
        DetailsApplication detailsApp = new DetailsApplication();
        detailsApp.start(new Stage());
    }
}