package com.example.supinmiton;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DetailsController implements Initializable {

    private final Map<String, String> themeMapping = new HashMap<>();

    private boolean isMusicPlaying = true;
    private Clip clip;

    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox themeCombobox;

    @FXML
    private Button pause;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initializeThemeMapping();
        this.themeCombobox.setItems(FXCollections.observableArrayList(this.themeMapping.keySet()));
        this.themeCombobox.getSelectionModel().select("French");
        this.startTheme();
    }

    private void initializeThemeMapping() {
        this.themeMapping.put("Main", "test.wav");
        this.themeMapping.put("English", "song_usa.wav");
        this.themeMapping.put("Spanish", "song_esp.wav");
        this.themeMapping.put("Chinese", "song_china.wav");
        this.themeMapping.put("French", "song_fra.wav");
    }

    @FXML
    protected void startTheme( ){
        String selectedKey = (String) this.themeCombobox.getSelectionModel().getSelectedItem();
        System.out.println(selectedKey);
        String selectedItem = this.themeMapping.get(selectedKey);
        System.out.println(selectedItem);
        Thread theme = new Thread(() -> playTheme("resources/"+selectedItem));
        theme.start();
    }

    @FXML
    protected void playTheme(String path){
        changeMusic();
        try {
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void changeMusic() {
        if (isMusicPlaying) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.stopTheme();
    }

    @FXML
    private void stopTheme() {
        if (isMusicPlaying) {
            if (clip != null && clip.isRunning()) {
                this.pause.setText("Resume");
                clip.stop();
                isMusicPlaying = false;
            }
        } else {
            clip.start();
            this.pause.setText("Pause");
            isMusicPlaying = true;
        }
    }
}