package com.supinfo.java.day2.exo5.model;

/**
 * To share data among several elements.
 */
public interface TextModel extends TextEventsSubscriber {
    /**
     * Update text data for the whole application.
     *
     * @param text new content.
     */
    void updateTextData(String text);

    /**
     * Read text data currently set.
     *
     * @return current content.
     */
    String getTextData();
}
