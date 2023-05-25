package com.supinfo.jee.casino;

import com.supinfo.jee.casino.gambler.Gambler;
import com.supinfo.jee.casino.gambler.GamblerRepository;
import com.supinfo.jee.casino.party.Party;
import com.supinfo.jee.casino.party.PartyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Princ {

    public static void main(String[] args) {
        SpringApplication.run(Princ.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(GamblerRepository gamblerRepository, PartyRepository partyRepository) {
        return args -> {
            Gambler user = new Gambler();
            user.setPseudo("pigeon");
            user.setBalance(5000);
            user.setBet(25);

            user = gamblerRepository.save(user);
            gamblerRepository.save(new Gambler("toto", 500, 100));
            gamblerRepository.save(new Gambler("titi", 500, 100));
            gamblerRepository.save(new Gambler("tata", 500, 100));
            gamblerRepository.save(new Gambler("tutu", 500, 100));
            gamblerRepository.save(new Gambler("indebted", -50, 100));
            gamblerRepository.save(new Gambler("looser", 5, 5));

            gamblerRepository.findAll().forEach(parieur -> log.info("Created gambler = {}.", parieur));

            Party party = new Party();
            party.setInitialValue(45);
            party.setDiceThrowCounter(5);
            party.setGambler(user);

            partyRepository.save(party);

            partyRepository.findAll().forEach(parties -> log.info("Created party = {}.", parties));
        };
    }
}
