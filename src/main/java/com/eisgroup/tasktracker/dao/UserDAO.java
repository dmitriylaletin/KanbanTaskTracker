package com.eisgroup.tasktracker.dao;

import com.eisgroup.tasktracker.model.User;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:00
 */
public interface UserDAO {
    void addUser(User user);

    void updateUser(User user);

    User getUserByLogin(String login);

    User getUserByUuid(String uuid);
}
