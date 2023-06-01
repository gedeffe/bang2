package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.credits.WrongAmountException;
import com.supinfo.jee.casino.game.EmptyPseudoException;
import com.supinfo.jee.casino.game.WrongBalanceException;
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
}
