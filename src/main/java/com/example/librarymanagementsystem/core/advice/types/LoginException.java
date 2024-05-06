package com.example.librarymanagementsystem.core.advice.types;

import javax.naming.AuthenticationException;

public class LoginException extends RuntimeException {
    public LoginException(String explanation) {
        super(explanation);
    }
}
