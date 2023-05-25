package com.supinfo.jee.casino.launches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaunchInputDto {
    private String pseudo;
    private int initialValue;
    private int bet;
    private int numberOfLaunch;
}
