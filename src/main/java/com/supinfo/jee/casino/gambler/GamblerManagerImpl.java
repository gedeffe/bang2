package com.supinfo.jee.casino.gambler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
