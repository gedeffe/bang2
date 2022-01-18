package com.supinfo.java.day4.ReaderWriter;

public class Reader implements Runnable {

    private final MailBox mailBox;

    public Reader(MailBox mailBox) {
        this.mailBox = mailBox;
    }


    @Override
    public void run() {
        String message = null;
        try {
            message = this.mailBox.pop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }
}
