package com.example.librarymanagementsystem.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String email;
    private String password;
    private String token;
}
