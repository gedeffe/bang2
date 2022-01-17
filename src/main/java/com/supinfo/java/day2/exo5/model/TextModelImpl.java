package com.supinfo.java.day2.exo5.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete class of data layer (model).
 */
public class TextModelImpl implements TextModel {

    private final List<TextEvents> listeners = new ArrayList<>();

    /*
    Use a file located in build folder to persist information.
     */
    private final Path dummyDatabase = Path.of("build", "dummyDatabase.txt");

    @Override
    public void updateTextData(final String text) {
        // update our persistence layer
        this.writeTextToFile(text);
        // send a events to update listeners
        this.listeners.forEach((listener) -> listener.notifyTextModified(text));
    }

    private void writeTextToFile(String text) {
        try {
            // replace current text
            Files.writeString(this.dummyDatabase, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTextData() {
        String text = null;
        try {
            text = Files.readString(this.dummyDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public void register(final TextEvents listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    @Override
    public void unregister(final TextEvents listener) {
        this.listeners.remove(listener);
    }
}
