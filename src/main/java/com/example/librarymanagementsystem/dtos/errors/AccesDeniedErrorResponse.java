package com.example.librarymanagementsystem.dtos.errors;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import org.springframework.http.HttpStatus;

public class AccesDeniedErrorResponse extends ErrorDetailResponse{
    public AccesDeniedErrorResponse(HttpStatus status, String detail) {
        setTitle("AccesDenied rule violation");
        setStatus(status);
        setErrorEnum(EnumErrorCode.ACCES_DENIED_ERROR.getErrorValue());
        setCode(status.value());
        setDetail(detail);
    }
}
