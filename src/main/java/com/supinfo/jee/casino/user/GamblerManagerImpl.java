package com.supinfo.jee.casino.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GamblerManagerImpl implements GamblerManager {

    private GamblerRepository gamblerRepository;

    @Override
    public void addUser(Gambler user) {

    }

    @Override
    public Gambler getUser(String pseudo) {
        return null;
    }
}
