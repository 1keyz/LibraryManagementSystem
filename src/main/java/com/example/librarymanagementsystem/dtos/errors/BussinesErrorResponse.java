package com.example.librarymanagementsystem.dtos.errors;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import org.springframework.http.HttpStatus;

public class BussinesErrorResponse extends ErrorDetailResponse{
    public BussinesErrorResponse(String detail , HttpStatus status) {
        setTitle("BusinessException Rule Violation");
        setErrorEnum(EnumErrorCode.GENERAL.getErrorValue());
        setStatus(status);
        setCode(status.value());
        setDetail(detail);
    }
}
