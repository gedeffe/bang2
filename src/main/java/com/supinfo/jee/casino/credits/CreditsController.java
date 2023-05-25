package com.supinfo.jee.casino.credits;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import com.supinfo.jee.casino.game.EmptyPseudoException;
import com.supinfo.jee.casino.game.WrongBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreditsController {

    private final GamblerManager gamblerManager;

    @PostMapping("/credits")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditsOutputDto payToWin(@RequestBody CreditsInputDto newCredits) {
        String pseudo = newCredits.getPseudo();
        CreditsOutputDto result = new CreditsOutputDto();
        Gambler gambler = gamblerManager.getGambler(pseudo);
        long balance = gambler.getBalance();
        if (StringUtils.hasText(pseudo)) {
            result.setPseudo(pseudo);
            if (newCredits.getAmount() > 1) {
                result.setNewBalance(balance + newCredits.getAmount());
                result.setAmount(newCredits.getAmount());
                if (result.getNewBalance() < 1) {
                    throw new WrongBalanceException(result.getNewBalance(), pseudo);
                }
            } else {
                throw new WrongAmountException();
            }
        } else {
            throw new EmptyPseudoException();
        }
        return result;
    }

}
