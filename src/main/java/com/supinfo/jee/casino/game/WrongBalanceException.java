package com.supinfo.jee.casino.game;

public class WrongBalanceException extends RuntimeException {
    public WrongBalanceException(long balance) {
        super("Please, pay to play ! Your balance is " + balance + " €");
    }
}
