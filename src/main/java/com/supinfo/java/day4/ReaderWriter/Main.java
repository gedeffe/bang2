package com.supinfo.java.day4.ReaderWriter;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(final String[] args) {
        MailBox mailBox = new MailBox();
        Reader reader = new Reader(mailBox);
        Writer writer = new Writer(mailBox);
        Writer writer1 = new Writer(mailBox);
        Writer writer2 = new Writer(mailBox);
        
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.scheduleAtFixedRate(writer, 10, 250, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(writer1, 10, 400, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(writer2, 10, 320, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(reader, 0, 700, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(reader, 10, 300, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(reader, 10, 300, TimeUnit.MILLISECONDS);
    }
}
