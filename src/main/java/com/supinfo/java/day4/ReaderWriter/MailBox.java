package com.supinfo.java.day4.ReaderWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MailBox {
    private final List<String> inbox = new ArrayList<>();
    Semaphore readLock = new Semaphore(0);
    Semaphore writeLock = new Semaphore(1);

    public void deposit(String message) throws InterruptedException {
        this.writeLock.acquire();
        System.out.println("Fonction deposit");
        this.inbox.add(message);
        this.readLock.release();
    }

    public String remove() throws InterruptedException {
        this.readLock.acquire();
        System.out.println("Fonction remove");
        String message = this.inbox.get(0);
        this.inbox.remove(0);
        this.writeLock.release();

        return message;
    }
}
