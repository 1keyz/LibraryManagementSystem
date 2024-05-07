package com.example.librarymanagementsystem.dtos.errors;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import org.springframework.http.HttpStatus;


public class LoginErrorResponse extends ErrorDetailResponse{
    public LoginErrorResponse(HttpStatus status ,String detail) {
        setTitle("Login fail!");
        setStatus(status);
        setErrorEnum(EnumErrorCode.LOGIN_ERROR.getErrorValue());
        setCode(status.value());
        setDetail(detail);
    }
}
