package com.supinfo.jee.casino.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CasinoController {

    @PostMapping("/dicestartermng")
    public String diceStartManagement(@ModelAttribute DiceStarter diceStarter, Model model) {
        // call backend to retrieve next step to take
        model.addAttribute("pseudo", diceStarter.getPseudo());
        return "redirect:/pay";
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
    public String pay(@RequestParam(name = "pseudo", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "pay";
    }

}
