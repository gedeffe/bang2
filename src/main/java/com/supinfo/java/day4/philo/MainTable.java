package com.supinfo.java.day4.philo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MainTable {

    public static void main(String[] args) {
        Semaphore fork1 = new Semaphore(1);
        Semaphore fork2 = new Semaphore(1);
        Semaphore fork3 = new Semaphore(1);
        Semaphore fork4 = new Semaphore(1);
        Semaphore fork5 = new Semaphore(1);

        Philosopher philosopher1 = new Philosopher(fork1, fork2, "Platon", 1000);
        Philosopher philosopher2 = new Philosopher(fork2, fork3, "Aristote", 2000);
        Philosopher philosopher3 = new Philosopher(fork3, fork4, "Kant", 5000);
        Philosopher philosopher4 = new Philosopher(fork4, fork5, "Sartre", 2500);
        Philosopher philosopher5 = new Philosopher(fork5, fork1, "Simone de Beauvoir", 3000);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(philosopher1);
        executorService.execute(philosopher2);
        executorService.execute(philosopher3);
        executorService.execute(philosopher4);
        executorService.execute(philosopher5);
    }
}
