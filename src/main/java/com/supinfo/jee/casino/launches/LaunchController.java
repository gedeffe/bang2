package com.supinfo.jee.casino.launches;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API to launch a dice (one or more time)", description = "Here is an endpoint to finally play with dice. It implies that current user has enough credits to play.")
@RestController
@RequiredArgsConstructor
public class LaunchController {

    private final GamblerManager gamblerManager;

    @Operation(summary = "Start a new Dice game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game started for selected player.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaunchOutputDto.class))}),
            @ApiResponse(responseCode = "402", description = "Player's account has not enough credits to play.",
                    content = @Content),
            @ApiResponse(responseCode = "412", description = "Pseudo is required and should be at least 3 characters.",
                    content = @Content)})
    @PostMapping("/launches")
    @ResponseStatus(HttpStatus.CREATED)
    public LaunchOutputDto play(@RequestBody LaunchInputDto newLaunch) {
        String pseudo = newLaunch.getPseudo();
        Gambler gambler = this.gamblerManager.playGame(pseudo, newLaunch.getInitialValue(), newLaunch.getBet(), newLaunch.getNumberOfLaunch());
        return new LaunchOutputDto(pseudo, gambler.getBalance());
    }
}
