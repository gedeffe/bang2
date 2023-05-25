package com.supinfo.jee.casino.credits;

import com.supinfo.jee.casino.game.EmptyPseudoException;
import com.supinfo.jee.casino.game.GameOutputDto;
import com.supinfo.jee.casino.game.WrongBalanceException;
import com.supinfo.jee.casino.launches.LaunchController;
import com.supinfo.jee.casino.launches.LaunchDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditsController {
    @PostMapping("/credits")
    @ResponseStatus()
    public EntityModel<GameOutputDto> payToWin(@RequestBody CreditsDto newCredits)  {
        String pseudo = newCredits.getPseudo();
        GameOutputDto result = new GameOutputDto(pseudo);
        if (StringUtils.hasText(pseudo)) {
            newCredits.setPseudo(pseudo);
            if (newCredits.getAmount() > 1) {
                result.setBalance(newCredits.getAmount());
                result.setBet(newCredits.getAmount());
            } else {
                throw new WrongAmountException();
            }
        } else {
            throw new EmptyPseudoException();
        }

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LaunchController.class).play(new LaunchDto())).withRel("launches");
        return EntityModel.of(result, link);
    }

}
