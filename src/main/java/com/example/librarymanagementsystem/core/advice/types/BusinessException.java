package com.example.librarymanagementsystem.core.advice.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
