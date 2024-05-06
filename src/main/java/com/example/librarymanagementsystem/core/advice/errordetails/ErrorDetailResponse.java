package com.example.librarymanagementsystem.core.advice.errordetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetailResponse {
    private HttpStatus status;
    private String errorEnum;
    private String title;
    private String detail;
    private int code;
}
