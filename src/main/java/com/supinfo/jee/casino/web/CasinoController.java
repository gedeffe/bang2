package com.supinfo.jee.casino.web;

import com.supinfo.jee.casino.api.GameApi;
import com.supinfo.jee.casino.api.GameInputDto;
import com.supinfo.jee.casino.api.GameOutputDto;
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
            target = "redirect:/";
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
        String name = String.valueOf(httpSession.getAttribute("pseudo"));
        model.addAttribute("pseudo", name);
        return "dice-roll";
    }

}
