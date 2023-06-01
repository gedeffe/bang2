package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.credits.WrongAmountException;
import com.supinfo.jee.casino.game.EmptyPseudoException;
import com.supinfo.jee.casino.game.WrongBalanceException;
import com.supinfo.jee.casino.launches.WrongBetException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class GamblerManagerImpl implements GamblerManager {

    private final GamblerRepository gamblerRepository;


    @Override
    public Gambler getGambler(String pseudo) {
        final Gambler gambler;
        if (this.gamblerRepository.existsByPseudo(pseudo)) {
            gambler = this.gamblerRepository.findByPseudo(pseudo);
        } else {
            Gambler newGambler = new Gambler(pseudo);
            gambler = this.gamblerRepository.save(newGambler);
        }
        return gambler;
    }

    @Override
    public Gambler creditBalance(String pseudo, int amount) {
        if (StringUtils.hasText(pseudo)) {
            if (amount > 1) {
                Gambler gambler = this.getGambler(pseudo);
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
        Gambler gambler = this.getGambler(pseudo);

        long newBalance = gambler.getBalance() - (long) bet * numberOfLaunch;
        gambler.setBalance(newBalance);
        gambler = this.gamblerRepository.save(gambler);

        if (newBalance < 1) {
            throw new WrongBalanceException(newBalance, pseudo);
        }
        return gambler;
    }
}
