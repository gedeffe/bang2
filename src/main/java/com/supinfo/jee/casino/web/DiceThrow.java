package com.supinfo.jee.casino.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DiceThrow {
    private int betAmount;
    private int betNumber;
    private int winChance;
}
