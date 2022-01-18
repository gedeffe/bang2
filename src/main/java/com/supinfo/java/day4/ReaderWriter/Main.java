package com.supinfo.java.day4.ReaderWriter;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(final String[] args) {
        MailBox mailBox = new MailBox();
        Reader reader = new Reader(mailBox);
        Writer writer = new Writer(mailBox);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(writer, 0, 500, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(reader, 0, 500, TimeUnit.MILLISECONDS);
    }
}
