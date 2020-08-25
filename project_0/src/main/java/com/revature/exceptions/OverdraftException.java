package com.revature.exceptions;

public class OverdraftException extends RuntimeException{
    /**
     *   OverdraftException: Used because this banking application does not support overdrafting.
     */
    public OverdraftException(String message) {
        super(message);
    }

    public OverdraftException() {
        super("This account does not support overdrafting.");
    }
}
