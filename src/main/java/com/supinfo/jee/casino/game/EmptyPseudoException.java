package com.supinfo.jee.casino.game;

public class EmptyPseudoException extends RuntimeException {
    public EmptyPseudoException() {
        super("Pseudo attribute should have a valid value !");
    }
}
