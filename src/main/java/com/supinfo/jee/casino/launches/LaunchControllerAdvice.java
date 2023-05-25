package com.supinfo.jee.casino.launches;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LaunchControllerAdvice {
    @ResponseBody
    @ExceptionHandler(WrongBetException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String wrongBetHandler(WrongBetException ex) {
        return ex.getMessage();
    }

}

