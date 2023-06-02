package com.supinfo.jee.casino.gambler;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Password is not correct !");
    }
}
