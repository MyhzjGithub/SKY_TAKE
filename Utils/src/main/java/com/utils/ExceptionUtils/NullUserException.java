package com.utils.ExceptionUtils;

public class NullUserException extends RuntimeException{
    public NullUserException() {
    }

    public NullUserException(String message) {
        super(message);
    }
}
