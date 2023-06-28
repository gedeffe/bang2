package com.supinfo.jee.casino.gambler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GamblersController {

    private final GamblerManager gamblerManager;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public GamblerOutputDto registerGambler(@RequestBody GamblerInputDto newGambler) {

        Gambler gambler = this.gamblerManager.createGambler(newGambler.getPseudo(), newGambler.getPassword());

        GamblerOutputDto result = new GamblerOutputDto();
        result.setPseudo(gambler.getPseudo());
        return result;
    }

}
