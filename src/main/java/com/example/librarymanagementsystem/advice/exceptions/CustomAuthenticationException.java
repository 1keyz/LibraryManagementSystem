package com.example.librarymanagementsystem.advice.exceptions;

import org.springframework.security.core.AuthenticationException;

import java.security.SignatureException;

public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
