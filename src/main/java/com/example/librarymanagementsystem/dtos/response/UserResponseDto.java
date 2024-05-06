package com.example.librarymanagementsystem.dtos.response;


import com.example.librarymanagementsystem.dtos.UserAdressConverterDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private UserAdressConverterDto adressDto;
}
