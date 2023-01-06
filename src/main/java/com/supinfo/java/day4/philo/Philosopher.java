package com.supinfo.java.day4.philo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

    private final Semaphore rightFork;
    private final Semaphore leftFork;
    private final String name;
    private final int thinkingFactor;

    public Philosopher(Semaphore rightFork, Semaphore leftFork, String name, int thinkingFactor) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.name = name;
        this.thinkingFactor = thinkingFactor;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    private void think() {
        try {
            System.out.println(this.name + " is thinking");
            Document doc = Jsoup.connect("https://www.dicocitations.com/citationdujour.php").get();
            System.out.println(doc.title());

            Elements blockQuotes = doc.getElementsByClass("blockquote");
            for (Element blockQuote : blockQuotes) {
                Elements spans = blockQuote.getElementsByTag("span");
                for (Element span : spans) {
                    System.out.println(span.text());
                }
            }
            Thread.sleep(this.thinkingFactor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        try {
            this.rightFork.acquire();
            this.leftFork.acquire();
            System.out.println(this.name + " is eating");
            Thread.sleep(500);
            this.rightFork.release();
            this.leftFork.release();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
