package com.supinfo.jee.casino.gambler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GamblerInputDto {
    private String pseudo;
    private String password;
    private String matchingPassword;
}
