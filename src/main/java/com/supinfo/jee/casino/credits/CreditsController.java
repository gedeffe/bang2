package com.supinfo.jee.casino.credits;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditsController {
    @PostMapping("/credits")
    public CreditsDto payToWin(@RequestBody CreditsDto newCredits) {
        return newCredits;
    }
}
