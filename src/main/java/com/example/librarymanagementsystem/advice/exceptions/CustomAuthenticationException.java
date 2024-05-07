package com.example.librarymanagementsystem.advice.exceptions;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String explanation) {
        super(explanation);
    }
}
