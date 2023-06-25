package com.supinfo.jee.casino.gambler;

public class ExistingGamblerException  extends RuntimeException {
    public ExistingGamblerException() {
        super("Gambler already exist !");
    }
}
