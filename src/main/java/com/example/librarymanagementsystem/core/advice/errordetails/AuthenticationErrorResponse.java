package com.example.librarymanagementsystem.core.advice.errordetails;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthenticationErrorResponse extends ErrorDetailResponse{

    public AuthenticationErrorResponse(HttpStatus status,  String detail) {
        setTitle("AuthenticationException Rule Violation ");
        setStatus(status);
        setCode(status.value());
        setDetail(detail);
        setErrorEnum(EnumErrorCode.AUTHENTICATION_ERROR.getErrorValue());
    }
}
