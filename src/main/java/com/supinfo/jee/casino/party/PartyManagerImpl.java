package com.supinfo.jee.casino.party;

import com.supinfo.jee.casino.gambler.GamblerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartyManagerImpl implements PartyManager {

    private final GamblerManager gamblerManager;

    @Override
    public Party newParty() {
        return new Party();
    }
}
