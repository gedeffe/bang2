package com.supinfo.jee.casino.api;

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
}
