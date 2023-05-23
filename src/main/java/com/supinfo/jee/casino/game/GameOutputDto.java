package com.supinfo.jee.casino.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameOutputDto {
    private String pseudo;
    private long balance;
    private int bet;

    public GameOutputDto(final String pseudoParam) {
        this.pseudo = pseudoParam;
    }
}
