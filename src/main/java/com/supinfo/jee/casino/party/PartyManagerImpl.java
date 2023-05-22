package com.supinfo.jee.casino.party;

import com.supinfo.jee.casino.user.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PartyManagerImpl implements PartyManager {

    private final UserManager userManager;

    @Override
    public int newParty() {
        return 0;
    }
}
