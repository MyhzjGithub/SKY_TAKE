package com.utils.ExceptionUtils;

public class UsernameException extends RuntimeException{
    public UsernameException() {
    }

    public UsernameException(String message) {
        super(message);
    }
}
