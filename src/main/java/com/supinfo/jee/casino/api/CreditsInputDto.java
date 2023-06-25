package com.supinfo.jee.casino.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreditsInputDto {
    private String pseudo;
    private int amount;
}
