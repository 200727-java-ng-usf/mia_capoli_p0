package com.revature.exceptions;

public class AuthenticatorException extends RuntimeException{

    public AuthenticatorException(String message) {
        super(message);
    }

    public AuthenticatorException() {
        super("Authentication failed.");
    }
}
