package com.utils.ExceptionUtils;

public class LoginException extends RuntimeException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
