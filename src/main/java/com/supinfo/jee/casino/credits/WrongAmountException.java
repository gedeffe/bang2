package com.supinfo.jee.casino.credits;

public class WrongAmountException extends RuntimeException{
    public WrongAmountException() {
        super("Credit amount should be at least 1 € !");
    }
}
