package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.dtos.request.LoginRequestDto;
import com.example.librarymanagementsystem.dtos.request.UserRequestDto;
import com.example.librarymanagementsystem.dtos.response.LoginResponseDto;
import com.example.librarymanagementsystem.dtos.response.UserResponseDto;
import com.example.librarymanagementsystem.model.entities.User;

public interface AuthService {
    UserResponseDto userRegister(UserRequestDto requestDto);

    LoginResponseDto userLogin(LoginRequestDto requestDto);
    User getCurrentUser();
}
