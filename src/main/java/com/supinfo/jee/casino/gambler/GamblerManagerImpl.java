package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.launches.WrongBetException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GamblerManagerImpl implements GamblerManager {

    private final GamblerRepository gamblerRepository;


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
            Gambler gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);

            if (!password.startsWith("{bcryp}") && !gambler.getPassword().equals(password)) {
                throw new WrongPasswordException();
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

        long newBalance = gambler.getBalance() - (long) bet * numberOfLaunch;
        gambler.setBalance(newBalance);
        gambler = this.gamblerRepository.save(gambler);

        if (newBalance < 1) {
            throw new WrongBalanceException(newBalance, pseudo);
        }
        return gambler;
    }


}
