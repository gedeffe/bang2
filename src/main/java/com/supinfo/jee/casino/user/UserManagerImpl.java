package com.supinfo.jee.casino.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserManagerImpl implements UserManager {
    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUser(String pseudo) {
        return null;
    }
}
