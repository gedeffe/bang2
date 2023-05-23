package com.supinfo.jee.casino.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GameControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EmptyPseudoException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String employeeNotFoundHandler(EmptyPseudoException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongBalanceException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    String wrongBalanceHandler(WrongBalanceException ex) {
        return ex.getMessage();
    }
}
