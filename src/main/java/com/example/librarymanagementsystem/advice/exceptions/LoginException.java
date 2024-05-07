package com.example.librarymanagementsystem.advice.exceptions;

public class LoginException extends RuntimeException {
    public LoginException(String explanation) {
        super(explanation);
    }
}
