package com.supinfo.jee.casino.launches;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaunchInputDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String pseudo;
    @NotBlank
    @Min(2)
    @Max(98)
    private int initialValue;
    @NotBlank
    @Min(1)
    private int bet;
    @NotBlank
    @Min(1)
    private int numberOfLaunch;
}
