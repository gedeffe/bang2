package com.supinfo.jee.casino.user;

public interface UserManager {
    void addUser(User user);

    User getUser(String pseudo);
}
