package com.example.librarymanagementsystem.core.advice.errordetails;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import lombok.Builder;
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
