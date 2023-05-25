package com.supinfo.jee.casino.launches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaunchOutputDto {
    private String pseudo;
    private long newBalance;

    public LaunchOutputDto(String pseudo, long balance) {
        this.pseudo = pseudo;
        this.newBalance = balance;
    }
}
