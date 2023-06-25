package com.supinfo.jee.casino.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = "creditapi", url = "http://localhost:8081/")
public interface CreditApi {

    @PostMapping("/credits")
    @ResponseStatus(HttpStatus.CREATED)
    CreditsOutputDto payToWin(@RequestBody CreditsInputDto newCredits);
}
