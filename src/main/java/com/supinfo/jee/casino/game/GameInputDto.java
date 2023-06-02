package com.supinfo.jee.casino.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameInputDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String pseudo;
    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}
