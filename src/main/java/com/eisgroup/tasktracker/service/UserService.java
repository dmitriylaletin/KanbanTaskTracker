package com.eisgroup.tasktracker.service;

import com.eisgroup.tasktracker.model.User;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:25
 */
public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    User getUserByLogin(String login);

    User getUserByUuid(String uuid);

    int getIdByLogin(String login);

    void addNewUser(String login, String password, String email);
}
