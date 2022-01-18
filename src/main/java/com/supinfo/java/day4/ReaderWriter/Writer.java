package com.supinfo.java.day4.ReaderWriter;


public class Writer implements Runnable {

    private final MailBox mailBox;

    public Writer(MailBox mailBox) {

        this.mailBox = mailBox;
    }

    @Override
    public void run() {
        String testMessage = "Bonjour";
        try {
            this.mailBox.deposit(testMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
