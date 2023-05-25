package com.supinfo.jee.casino.launches;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import com.supinfo.jee.casino.game.EmptyPseudoException;
import com.supinfo.jee.casino.game.WrongBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LaunchController {
    private final GamblerManager gamblerManager;

    @PostMapping("/launches")
    @ResponseStatus(HttpStatus.CREATED)
    public LaunchOutputDto play(@RequestBody LaunchInputDto newLaunch) {
        String pseudo = newLaunch.getPseudo();
        if (pseudo == null || pseudo.isEmpty()) {
            throw new EmptyPseudoException();
        }
        if (newLaunch.getBet() < 1) {
            throw new WrongBetException();
        }
        Gambler gambler = this.gamblerManager.getGambler(pseudo);
        LaunchOutputDto launchOutputDto = new LaunchOutputDto(pseudo, gambler.getBalance() - (long) newLaunch.getBet() * newLaunch.getNumberOfLaunch());

        if (launchOutputDto.getNewBalance() < 1) {
            throw new WrongBalanceException(launchOutputDto.getNewBalance(), pseudo);
        }
        return launchOutputDto;
    }

}
