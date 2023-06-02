package com.supinfo.jee.casino.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LaunchOutputDto {
    private String pseudo;
    private long newBalance;

    public LaunchOutputDto(String pseudo, long balance) {
        this.pseudo = pseudo;
        this.newBalance = balance;
    }
}
