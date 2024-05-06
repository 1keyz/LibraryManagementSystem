package com.example.librarymanagementsystem.controllers;


import com.example.librarymanagementsystem.dtos.request.LoginRequestDto;
import com.example.librarymanagementsystem.dtos.request.UserRequestDto;
import com.example.librarymanagementsystem.dtos.response.LoginResponseDto;
import com.example.librarymanagementsystem.dtos.response.UserResponseDto;
import com.example.librarymanagementsystem.security.jwt.JwtHelper;
import com.example.librarymanagementsystem.services.abstracts.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> userRegister(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(authService.userRegister(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(authService.userLogin(requestDto));
    }
}
