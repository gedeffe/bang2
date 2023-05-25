package com.supinfo.jee.casino.credits;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditsOutputDto {
    private String pseudo;
    private int amount;
    private long newBalance;
}
