package com.supinfo.jee.casino.launches;

public class WrongBetException extends RuntimeException {
    public WrongBetException() {
        super("Bet attribute should have a valid value !");
    }
}
