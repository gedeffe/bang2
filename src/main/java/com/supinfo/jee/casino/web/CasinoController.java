package com.supinfo.jee.casino.web;

import com.supinfo.jee.casino.api.*;
import feign.FeignException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CasinoController {

    private final GameApi gameApi;
    private final LaunchApi launchApi;

    @GetMapping("/dicestartermng")
    public String diceStartManagement(HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String pseudo = String.valueOf(authentication.getPrincipal());
        log.info("try to start game for {}.", pseudo);
        httpSession.setAttribute("pseudo", pseudo);

        GameInputDto newGame = new GameInputDto(pseudo, null);
        String target;
        try {
            EntityModel<GameOutputDto> gameOutputDtoEntityModel = this.gameApi.newGame(newGame);
            GameOutputDto gameOutputDto = gameOutputDtoEntityModel.getContent();
            if (gameOutputDto == null) {
                log.warn("Error with backend result for {}", pseudo);
                target = "redirect:/";
            } else {
                httpSession.setAttribute("bet", gameOutputDto.getBet());
                httpSession.setAttribute("balance", gameOutputDto.getBalance());

                log.info("Successful game start for {}", pseudo);
                target = "redirect:/dice-roll";
            }
        } catch (FeignException.FeignClientException e) {
            log.error("Unable to work with this player {} !", pseudo, e);
            if (e.status() == 403) {
                target = "redirect:/login";
            } else {
                target = "redirect:/pay";
            }
        }
        return target;
    }

    @PostMapping("/addcredits")
    public String creditsManagement(@ModelAttribute Credits credits, Model model) {
        // call backend to retrieve next step to take
        model.addAttribute("amount", credits.getAmount());
        return "redirect:/";
    }


    @GetMapping("/login")
    public String connexion() {
        return "Connection";
    }

    @GetMapping("/pay")
    public String pay(Model model, HttpSession httpSession) {
        String name = String.valueOf(httpSession.getAttribute("pseudo"));
        model.addAttribute("pseudo", name);
        return "pay";
    }

    @GetMapping("/dice-roll")
    public String diceRoll(Model model, HttpSession httpSession) {
        DiceThrow diceThrow = new DiceThrow();
        model.addAttribute("diceThrow", diceThrow);
        String name = String.valueOf(httpSession.getAttribute("pseudo"));
        model.addAttribute("pseudo", name);
        Integer bet = (Integer) httpSession.getAttribute("bet");
        diceThrow.setBetAmount(Objects.requireNonNullElse(bet, 1));
        Long balance = (Long) httpSession.getAttribute("balance");
        model.addAttribute("balance", Objects.requireNonNullElse(balance, 0));

        return "dice-roll";
    }

    @PostMapping(value = "/throw-dice")
    public String throwDice(@ModelAttribute DiceThrow diceThrow, HttpSession httpSession) {
        log.info(String.valueOf(diceThrow));
        String pseudo = String.valueOf(httpSession.getAttribute("pseudo"));
        int bet = diceThrow.getBetAmount();
        int initialValue = diceThrow.getWinChance();
        int numberOfLaunch = diceThrow.getBetNumber();
        LaunchInputDto newLaunch = new LaunchInputDto(pseudo, initialValue, bet, numberOfLaunch);
        String target;
        try {
            LaunchOutputDto launchOutputDto = this.launchApi.play(newLaunch);
            httpSession.setAttribute("balance", launchOutputDto.getNewBalance());
            target = "redirect:/dice-roll";
        } catch (FeignException.FeignClientException e) {
            log.error("Unable to work with this player {} !", pseudo, e);
            target = "redirect:/pay";
        }
        return target;
    }


}
