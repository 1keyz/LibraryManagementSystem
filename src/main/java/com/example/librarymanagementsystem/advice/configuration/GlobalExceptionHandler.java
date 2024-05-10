package com.example.librarymanagementsystem.advice.configuration;

import com.example.librarymanagementsystem.advice.exceptions.BusinessException;
import com.example.librarymanagementsystem.advice.exceptions.LoginException;
import com.example.librarymanagementsystem.advice.exceptions.NotFoundException;
import com.example.librarymanagementsystem.dtos.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.security.SignatureException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public AccesDeniedErrorResponse handleException(Exception ex) {
        AccesDeniedErrorResponse response = new AccesDeniedErrorResponse(HttpStatus.BAD_GATEWAY, ex.getMessage());
        return response;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationErrorResponse authenticationHandlerException(AuthenticationException ex) {
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
        return response;
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationErrorResponse signatureHandlerException(SignatureException ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
        return response;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BussinesErrorResponse bussinesHandlerException(RuntimeException ex){
        BussinesErrorResponse response = new BussinesErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundErrorDetailResponse notFoundHandlerException(RuntimeException ex){
        NotFoundErrorDetailResponse response = new NotFoundErrorDetailResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LoginErrorResponse loginHandlerException(RuntimeException ex){
        LoginErrorResponse response = new LoginErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorResponse validationHandlerException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> errorList = errors.stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());

        ValidationErrorResponse response = new ValidationErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY,errorList);
        return response;
    }
}
