package com.supinfo.jee.casino.gambler;

public class EmptyPseudoException extends RuntimeException {
    public EmptyPseudoException() {
        super("Pseudo attribute should have a valid value !");
    }
}
