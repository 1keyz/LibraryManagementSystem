package com.example.librarymanagementsystem.core.advice.types;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
