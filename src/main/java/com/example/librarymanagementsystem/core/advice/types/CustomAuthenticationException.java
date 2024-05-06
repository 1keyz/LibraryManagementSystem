package com.example.librarymanagementsystem.core.advice.types;

import javax.naming.AuthenticationException;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String explanation) {
        super(explanation);
    }
}
