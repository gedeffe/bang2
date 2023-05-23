package com.supinfo.jee.casino.launches;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LaunchController {

    @PostMapping("/launches")
    public LaunchDto play(@RequestBody LaunchDto newLaunch) {
        return newLaunch;
    }
}
