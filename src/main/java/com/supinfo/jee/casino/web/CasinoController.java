package com.supinfo.jee.casino.web;

import com.supinfo.jee.casino.api.*;
import feign.FeignException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CasinoController {

    private final GameApi gameApi;
    private final LaunchApi launchApi;

    @PostMapping("/dicestartermng")
    public String diceStartManagement(@ModelAttribute DiceStarter diceStarter, HttpSession httpSession) {
        // call backend to retrieve next step to take
        String pseudo = diceStarter.getPseudo();
        httpSession.setAttribute("pseudo", pseudo);

        GameInputDto newGame = new GameInputDto();
        newGame.setPseudo(pseudo);
        String target;
        try {
            EntityModel<GameOutputDto> gameOutputDtoEntityModel = this.gameApi.newGame(newGame);
            GameOutputDto gameOutputDto = gameOutputDtoEntityModel.getContent();
            httpSession.setAttribute("bet", gameOutputDto.getBet());
            httpSession.setAttribute("balance", gameOutputDto.getBalance());
            target = "redirect:/dice-roll";
        } catch (FeignException.FeignClientException e) {
            log.error("Unable to work with this player {} !", pseudo, e);
            target = "redirect:/pay";
        }
        return target;
    }

    @PostMapping("/addcredits")
    public String creditsManagement(@ModelAttribute Credits credits, Model model) {
        // call backend to retrieve next step to take
        model.addAttribute("amount", credits.getAmount());
        return "redirect:/";
    }


    @GetMapping("/connexion")
    public String connexion(@RequestParam(name = "pseudo", required = false) String name, Model model) {
        DiceStarter diceStarter = new DiceStarter();
        diceStarter.setPseudo(name);
        model.addAttribute("diceStarter", diceStarter);
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
        if (bet != null) {
            diceThrow.setBetAmount(bet);
        } else {
            diceThrow.setBetAmount(1);
        }
        Long balance = (Long) httpSession.getAttribute("balance");
        if (balance != null) {
            model.addAttribute("balance", balance);
        } else {
            model.addAttribute("balance", 0);
        }

        return "dice-roll";
    }

    @PostMapping(value = "/throw-dice")
    public String throwDice(@ModelAttribute DiceThrow diceThrow, Model model, HttpSession httpSession) {
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
