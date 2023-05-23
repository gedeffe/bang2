package com.supinfo.jee.casino.game;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GamblerManager gamblerManager;

    @PostMapping("/games")
    GameDto newGame(@RequestBody GameDto newGame) {
        /*
        Retrieve or create user associated to this new game. If gambler already exists, then provide values for balance and bet.
         */
        String pseudo = newGame.getPseudo();
        if (StringUtils.hasText(pseudo)) {
            Gambler gambler = this.gamblerManager.getGambler(pseudo);
            if (gambler.getBalance() > 0) {
                newGame.setBalance(gambler.getBalance());
                newGame.setBet(gambler.getBet());
            } else {
                throw new WrongBalanceException(gambler.getBalance());
            }
        } else {
            throw new EmptyPseudoException();
        }
        return newGame;
    }
}
