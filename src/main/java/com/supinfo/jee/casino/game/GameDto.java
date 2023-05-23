package com.supinfo.jee.casino.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto {
    private String pseudo;
    private long balance;
    private int bet;
}
