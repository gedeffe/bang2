package com.supinfo.jee.casino.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingsController {

    @GetMapping("/dice")
    public String greeting(@RequestParam(name = "pseudo", required = false, defaultValue = "gambler") String name, Model model) {
        model.addAttribute("pseudo", name);
        return "dice";
    }

    @PostMapping("/dicestartermng")
    public String diceStartManagement(@ModelAttribute DiceStarter diceStarter, Model model) {
        // call backend to retrieve next step to take
        model.addAttribute("pseudo", diceStarter.getPseudo());
        return "credits";
    }

    @PostMapping("/addcredits")
    public String creditsManagement(@ModelAttribute Credits credits, Model model) {
        // call backend to retrieve next step to take
        model.addAttribute("amount", credits.getAmount());
        return "dice";
    }

    @GetMapping("/connexion")
    public String connexion() {
        return "Connection";
    }

}
