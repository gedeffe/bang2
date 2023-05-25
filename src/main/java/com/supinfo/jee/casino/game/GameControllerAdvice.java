package com.supinfo.jee.casino.game;

import com.supinfo.jee.casino.credits.CreditsController;
import com.supinfo.jee.casino.credits.CreditsDto;
import com.supinfo.jee.casino.credits.NegativeAmountException;
import com.supinfo.jee.casino.credits.WrongAmountException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GameControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EmptyPseudoException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String employeeNotFoundHandler(EmptyPseudoException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(WrongAmountException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String wrongAmountHandler(WrongAmountException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongBalanceException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    EntityModel<Problem> wrongBalanceHandler(WrongBalanceException ex)  {
        Problem problem = Problem.create()
                .withType(URI.create("https://developer.mozilla.org/fr/docs/Web/HTTP/Status/402"))
                .withTitle("You do not have enough credit.")
                .withDetail(HttpStatus.PAYMENT_REQUIRED.getReasonPhrase() + ex.getMessage()) //
                .withProperties(map -> {
                    map.put("balance", ex.getBalance());
                    map.put("pseudo", ex.getPseudo());
                });
        CreditsDto newCredits = new CreditsDto();
        newCredits.setPseudo(ex.getPseudo());
        newCredits.setAmount(100);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CreditsController.class).payToWin(newCredits)).withRel("credits");
        return EntityModel.of(problem, link);
    }
}
