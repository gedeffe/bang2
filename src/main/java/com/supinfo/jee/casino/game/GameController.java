package com.supinfo.jee.casino.game;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import com.supinfo.jee.casino.launches.LaunchController;
import com.supinfo.jee.casino.launches.LaunchInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GameController {

    private final GamblerManager gamblerManager;

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<GameOutputDto> newGame(@RequestBody GameInputDto newGame) {
        /*
        Retrieve or create user associated to this new game. If gambler already exists, then provide values for balance and bet.
         */
        String pseudo = newGame.getPseudo();
        GameOutputDto result = new GameOutputDto(pseudo);
        if (StringUtils.hasText(pseudo)) {
            Gambler gambler = this.gamblerManager.getGambler(pseudo);
            if (gambler.getBalance() > 0) {
                result.setBalance(gambler.getBalance());
                result.setBet(gambler.getBet());
            } else {
                throw new WrongBalanceException(gambler.getBalance(), pseudo);
            }
        } else {
            throw new EmptyPseudoException();
        }

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LaunchController.class).play(new LaunchInputDto())).withRel("launches");
        return EntityModel.of(result, link);
    }
}
