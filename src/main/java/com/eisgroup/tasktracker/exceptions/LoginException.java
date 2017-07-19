package com.eisgroup.tasktracker.exceptions;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:08
 */
public class LoginException extends Exception{

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }
}
