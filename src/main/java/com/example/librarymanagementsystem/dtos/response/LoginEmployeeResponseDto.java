package com.example.librarymanagementsystem.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginEmployeeResponseDto {
    private String email;
    private String password;
    private String token;
}
