package com.revature.exceptions;

public class NegativeException extends RuntimeException{
    /**
     *   NegativeException: Used because money cannot be a negative or non zero value.
     */
    public NegativeException(String message) {
        super(message);
    }

    public NegativeException() {
        super("Please enter a positive, non-zero number!");
    }

}
