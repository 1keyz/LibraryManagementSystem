package com.example.librarymanagementsystem.core.advice.errordetails;

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
