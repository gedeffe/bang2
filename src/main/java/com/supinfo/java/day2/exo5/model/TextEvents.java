package com.supinfo.java.day2.exo5.model;

/**
 * To manage events about text edition.
 */
public interface TextEvents {

    /**
     * To notify listeners that our text has been modified, and they might want to update their inner state.
     *
     * @param text new content.
     */
    void notifyTextModified(String text);

}
