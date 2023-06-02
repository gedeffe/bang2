package com.supinfo.jee.casino.gambler;

public class WrongAmountException extends RuntimeException {
    public WrongAmountException() {
        super("Credit amount should be at least 1 € !");
    }
}
