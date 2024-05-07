package com.example.librarymanagementsystem.dtos.errors;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundErrorDetailResponse extends ErrorDetailResponse {

    public NotFoundErrorDetailResponse(String detail, HttpStatus status) {
        setTitle("NotFound Rule Violation");
        setDetail(detail);
        setStatus(status);
        setCode(status.value());
        setErrorEnum(EnumErrorCode.NOT_FOUND.getErrorValue());
    }
}
