package com.supinfo.jee.casino;

import com.supinfo.jee.casino.user.UserManager;
import org.springframework.stereotype.Service;

@Service
public class PartyManagerImpl implements PartyManager {

    private final UserManager userManager;

    public PartyManagerImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public int newParty() {
        return 0;
    }
}
