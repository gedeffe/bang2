package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.config.GamblerProperties;
import com.supinfo.jee.casino.launches.WrongBetException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GamblerManagerImpl implements GamblerManager {

    private final GamblerRepository gamblerRepository;
    private final GamblerProperties gamblerProperties;

    public void createGambler(String pseudo, String password) {
        if (StringUtils.hasText(pseudo)) {
            if (StringUtils.hasText(password)) {
                if (this.retrieveGambler(pseudo).isEmpty()) {
                    gamblerRepository.save(new Gambler(pseudo, password, 0, 25));
                    gamblerRepository.findAll().forEach(parieur -> log.info("Created gambler = {}.", parieur));
                } else {
                    throw new ExistingGamblerException();
                }
            } else {
                throw new EmptyPasswordException();
            }
        } else {
            throw new EmptyPseudoException();
        }
    }

    @Override
    public Gambler getGambler(String pseudo) {
        final Gambler gambler;
        if (StringUtils.hasText(pseudo)) {
            gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);
            if (gambler.getBalance() <= 0) {
                throw new WrongBalanceException(gambler.getBalance(), pseudo);
            }
        } else {
            throw new EmptyPseudoException();
        }
        return gambler;
    }

    @Override
    public void authenticateGambler(String pseudo, String password) {
        if (StringUtils.hasText(pseudo)) {
            if (this.retrieveGambler(pseudo).isEmpty()) {
                this.createGambler(pseudo, password);
            } else {
                Gambler gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);
                if (!password.startsWith("{bcryp}") && !gambler.getPassword().equals(password)) {
                    throw new WrongPasswordException();
                }
            }
        } else {
            throw new EmptyPseudoException();
        }
    }

    private Optional<Gambler> retrieveGambler(String pseudo) {
        final Optional<Gambler> gamblerOptional;
        if (this.gamblerRepository.existsByPseudo(pseudo)) {
            gamblerOptional = Optional.of(this.gamblerRepository.findByPseudo(pseudo));
        } else {
            gamblerOptional = Optional.empty();
        }
        return gamblerOptional;
    }

    @Override
    public Gambler creditBalance(String pseudo, int amount) {
        if (StringUtils.hasText(pseudo)) {
            if (amount > 1) {
                Gambler gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);
                long balance = gambler.getBalance();
                gambler.setBalance(balance + amount);
                gambler = this.gamblerRepository.save(gambler);
                if (gambler.getBalance() < 1) {
                    throw new WrongBalanceException(gambler.getBalance(), pseudo);
                }
                return gambler;
            } else {
                throw new WrongAmountException();
            }
        } else {
            throw new EmptyPseudoException();
        }
    }

    @Override
    public Gambler playGame(String pseudo, int initialValue, int bet, int numberOfLaunch) {
        if (pseudo == null || pseudo.isEmpty()) {
            throw new EmptyPseudoException();
        }
        if (bet < 1) {
            throw new WrongBetException();
        }
        Gambler gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);
        log.debug("Start bet ({}):", numberOfLaunch);

        for (int i = 0; i < numberOfLaunch; i++) {
            gambler.setBalance(gambler.getBalance() - (long) bet);
            if (gambler.getBalance() < 1) {
                gambler = this.gamblerRepository.save(gambler);
                throw new WrongBalanceException(gambler.getBalance(), pseudo);
            }
            if (this.gamblerProperties.isEnabled()) {
                int number = (int) (Math.random() * 100);
                log.debug("random dice roll {}", number);
                if (number <= initialValue) {
                    gambler.setBalance(gambler.getBalance() + (long) bet * 100 / initialValue);
                }
            }
        }
        gambler = this.gamblerRepository.save(gambler);
        if (gambler.getBalance() < 1) {
            throw new WrongBalanceException(gambler.getBalance(), pseudo);
        }
        return gambler;
    }
}
