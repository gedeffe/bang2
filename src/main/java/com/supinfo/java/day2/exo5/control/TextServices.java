package com.supinfo.java.day2.exo5.control;

/**
 * To clean text area or populate it with new text (if there is an existing text, new text will be added after it).
 */
public interface TextServices {
    /**
     * Reset content of displayed text. Current text will be erased.
     */
    void clearText();

    /**
     * Complete current displayed text with provided content on next line.
     *
     * @param text content to add after current paragraph.
     */
    void addText(String text);
}
