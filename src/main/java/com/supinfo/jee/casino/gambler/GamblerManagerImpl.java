package com.supinfo.jee.casino.gambler;

import com.supinfo.jee.casino.config.GamblerProperties;
import com.supinfo.jee.casino.launches.WrongBetException;
import com.supinfo.jee.casino.party.Party;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GamblerManagerImpl implements GamblerManager {

    private final GamblerRepository gamblerRepository;
    private final GamblerProperties gamblerProperties;

    @Override
    public Gambler createGambler(String pseudo, String password) {
        Gambler gambler;
        if (StringUtils.hasText(pseudo)) {
            if (StringUtils.hasText(password)) {
                if (this.retrieveGambler(pseudo).isEmpty()) {
                    gambler = gamblerRepository.save(new Gambler(pseudo, password, 0, 25));
                    log.info("Created gambler = {}.", gambler);
                } else {
                    throw new ExistingGamblerException();
                }
            } else {
                throw new EmptyPasswordException();
            }
        } else {
            throw new EmptyPseudoException();
        }
        return gambler;
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
            Gambler gambler = this.retrieveGambler(pseudo).orElseThrow(EmptyPseudoException::new);

            if (!password.startsWith("{bcryp}") && !gambler.getPassword().equals(password)) {
                throw new WrongPasswordException();
            }
            log.info("authenticates {} with success.", pseudo);
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

    private int getNumberOfWin(Gambler gambler) {
        List<Party> partyList = gambler.getPartyList();
        int numberOfWin = 0;
        int startIndex = Math.max(0, partyList.size() - 10); // Commencer à l'indice 0 ou l'indice correspondant aux 10 dernières parties
        for (int i = startIndex; i < partyList.size(); i++) {
            if (partyList.get(i).isWin()) {
                numberOfWin++;
            }
        }

        return numberOfWin;
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

        int recentWins = getNumberOfWin(gambler);
        for (int i = 0; i < numberOfLaunch; i++) {
            if (this.gamblerProperties.isEnabled()) {
                Party newParty = new Party();
                newParty.setGambler(gambler);
                newParty.setBet(bet);
                newParty.setDiceThrowCounter(initialValue);
                if (recentWins >= 5) {
                    // Si le joueur a gagné 5 parties sur les 10 dernières parties, il perd directement
                    gambler.setBalance(gambler.getBalance() - bet);
                }
                boolean isWin = false;
                int random = (int) (Math.random() * 98 + 1);
                log.debug("random dice roll {}", random);
                if (initialValue < random) {
                    isWin = true;
                    newParty.setWin(true);
                }

                if (!isWin || getNumberOfWin(gambler) + i >= 8) {
                    gambler.setBalance(gambler.getBalance() - bet);
                    newParty.setWin(false);
                    isWin = false;
                }

                log.debug("Has win ? {}", isWin);

                if (isWin) {
                    gambler.setBalance(gambler.getBalance() + (long) bet * 100 / initialValue);
                }

                gambler.getPartyList().add(newParty);
            } else {
                gambler.setBalance(gambler.getBalance() - (long) bet);
            }
        }

        gambler = this.gamblerRepository.save(gambler);

        if (gambler.getBalance() < 1) {
            throw new WrongBalanceException(gambler.getBalance(), pseudo);
        }
        return gambler;
    }


}
