package com.supinfo.java.day2.exo5.control;


import com.supinfo.java.day2.exo5.model.TextModel;


import java.io.*;

/**
 * Concrete class of services layer (control).
 */
public class TextServicesImpl implements TextServices {

    private final TextModel textModel;


    public TextServicesImpl(final TextModel textModelParam) {
        this.textModel = textModelParam;
    }

    @Override
    public void clearText() {
        this.textModel.updateTextData("");
    }

    @Override
    public void addText(final String text) {
        // retrieve current text
        final String currentText = this.textModel.getTextData();
        if (currentText.isEmpty()) {
            this.textModel.updateTextData(text);
        } else {
            // add a line feed then the new text
            this.textModel.updateTextData(currentText + "\n" + text);
        }

    }
    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
        writer.write(textModel.getTextData());
        writer.close();
        clearText();}
    public void load() throws IOException {
        File file = new File("C:\\Users\\olivi\\IdeaProjects\\bang2\\save.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null)
            addText(st);

    }
}

