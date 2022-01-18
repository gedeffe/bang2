package com.supinfo.java.day4;

class TicTac extends Thread {
    static boolean go = false;

    private TicTac() {
        System.out.println(Thread.currentThread());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread());
        Thread ticTac = new TicTac();

        ticTac.start();
        ticTac.join();
//        while (!go) {
//            sleep(10);
//        }
        System.out.print("tac");
    }

    public void run() {
        System.out.print("tic ");
        go = true;
    }
}