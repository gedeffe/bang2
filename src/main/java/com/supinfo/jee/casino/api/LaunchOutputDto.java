package com.supinfo.jee.casino.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaunchOutputDto {
    private String pseudo;
    private long newBalance;
}
