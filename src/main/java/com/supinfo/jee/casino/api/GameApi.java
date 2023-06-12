package com.supinfo.jee.casino.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = "gameapi", url = "http://localhost:8081/")
public interface GameApi {
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<GameOutputDto> newGame(@RequestBody GameInputDto newGame);

    @PostMapping("/authenticates")
    void authenticate(@RequestBody GameInputDto newGame);
}
