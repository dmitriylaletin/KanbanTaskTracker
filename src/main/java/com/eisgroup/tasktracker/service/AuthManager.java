package com.eisgroup.tasktracker.service;

import com.eisgroup.tasktracker.exceptions.LoginException;
import com.eisgroup.tasktracker.model.User;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:21
 */
public interface AuthManager {
    User getCurrentUser();

    void login(String login, String password, boolean rememberUser) throws LoginException;

    void loginFromServlet(String uuid, HttpServletRequest request) throws LoginException;

    void logout();
}
