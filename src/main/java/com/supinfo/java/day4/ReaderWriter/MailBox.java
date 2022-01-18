package com.supinfo.java.day4.ReaderWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MailBox {
    private final List<String> inbox = new ArrayList<>();
    Semaphore readLock = new Semaphore(0);

    Semaphore concurrentLock = new Semaphore(1);

    public void deposit(String message) throws InterruptedException {
        this.concurrentLock.acquire();
        System.out.println("Fonction deposit" + this.inbox.size());
        this.inbox.add(message);
        this.readLock.release();
        this.concurrentLock.release();
    }

    public String pop() throws InterruptedException {
        this.readLock.acquire();
        this.concurrentLock.acquire();
        System.out.println("Fonction remove" + this.inbox.size());
        String message = this.inbox.get(0);
        this.inbox.remove(0);
        this.concurrentLock.release();

        return message;
    }
}
