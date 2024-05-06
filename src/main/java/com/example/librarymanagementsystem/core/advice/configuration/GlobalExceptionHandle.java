package com.example.librarymanagementsystem.core.advice.configuration;


import com.example.librarymanagementsystem.core.advice.errordetails.*;
import com.example.librarymanagementsystem.core.advice.types.BusinessException;
import com.example.librarymanagementsystem.core.advice.types.CustomAuthenticationException;
import com.example.librarymanagementsystem.core.advice.types.LoginException;
import com.example.librarymanagementsystem.core.advice.types.NotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandle{

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundErrorDetailResponse handleNotFoundException(RuntimeException exception){
        NotFoundErrorDetailResponse response = new NotFoundErrorDetailResponse(exception.getMessage() ,HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BussinesErrorResponse handleBusinessException(RuntimeException exception){
        BussinesErrorResponse response = new BussinesErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();

        for (FieldError x : fieldErrors){
            errors.add(x.getDefaultMessage());
        }
        ValidationErrorResponse response = new ValidationErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY,errors);
        return response;
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LoginErrorResponse handleLoginException(RuntimeException exception) {
        LoginErrorResponse response = new LoginErrorResponse(HttpStatus.BAD_REQUEST,exception.getMessage());
        return response;
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationErrorResponse handleAuthenticationException(Exception ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
        return response;
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationErrorResponse handleAccountStatusException(AccountStatusException ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
        return response;
    }
    @ExceptionHandler(CustomAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationErrorResponse handleInvalidBearerTokenException(AuthenticationException ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.UNAUTHORIZED,ex.getMessage());
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AuthenticationErrorResponse handleAccesDeniedException(AccessDeniedException ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.FORBIDDEN,ex.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthenticationErrorResponse handleOtherException(Exception ex){
        AuthenticationErrorResponse response = new AuthenticationErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        return response;
    }
}
