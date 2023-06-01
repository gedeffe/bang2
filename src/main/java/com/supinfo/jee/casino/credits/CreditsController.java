package com.supinfo.jee.casino.credits;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Gambler gambler = gamblerManager.creditBalance(pseudo, newCredits.getAmount());
        result.setNewBalance(gambler.getBalance());
        result.setAmount(newCredits.getAmount());
        result.setPseudo(pseudo);
        return result;
    }

}
