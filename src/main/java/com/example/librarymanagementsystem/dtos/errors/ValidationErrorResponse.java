package com.example.librarymanagementsystem.dtos.errors;

import com.example.librarymanagementsystem.model.enums.EnumErrorCode;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidationErrorResponse extends ErrorDetailResponse{
    List<String> errors;

    public ValidationErrorResponse(HttpStatus status,List<String> errors) {
        this.errors = errors;
        setTitle("ValidationException Rule Violation");
        setErrorEnum(EnumErrorCode.VALIDATION_ERROR.getErrorValue());
        setDetail("Validation Exception");
        setCode(status.value());
    }
}
