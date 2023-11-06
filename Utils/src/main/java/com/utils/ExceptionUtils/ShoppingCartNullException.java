package com.utils.ExceptionUtils;

public class ShoppingCartNullException extends RuntimeException{
    public ShoppingCartNullException() {
    }

    public ShoppingCartNullException(String message) {
        super(message);
    }
}
