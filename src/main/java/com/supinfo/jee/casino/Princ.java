package com.supinfo.jee.casino;

import com.supinfo.jee.casino.user.Gambler;
import com.supinfo.jee.casino.user.GamblerRepository;
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
    public CommandLineRunner initDatabase(GamblerRepository repository) {
        return args -> {
            Gambler user = new Gambler();
            user.setPseudo("pigeon");
            user.setBalance(5000);
            user.setBet(25);

            repository.save(user);
            repository.save(new Gambler("toto", 500, 100));
            repository.save(new Gambler("titi", 500, 100));
            repository.save(new Gambler("tata", 500, 100));
            repository.save(new Gambler("tutu", 500, 100));

            repository.findAll().forEach(parieur -> log.info("Created user = {}.", parieur));
        };
    }
}
