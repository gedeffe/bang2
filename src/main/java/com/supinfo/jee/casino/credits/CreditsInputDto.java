package com.supinfo.jee.casino.credits;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditsInputDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String pseudo;
    @NotBlank
    @Min(1)
    private int amount;
}
