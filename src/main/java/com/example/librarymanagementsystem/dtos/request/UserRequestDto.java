package com.example.librarymanagementsystem.dtos.request;

import com.example.librarymanagementsystem.model.enums.RoleEnum;
import com.example.librarymanagementsystem.model.enums.UserStatusLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDto {
    @NotBlank(message = "You must give firstname !")
    private String firstName;
    @NotBlank(message = "You must give firstname !")
    private String lastName;
    @NotBlank(message = "Email doesn't null !")
    @Email(message = "Email does not fit the format : user@host.com")
    private String email;
    @NotBlank(message = "Password doesn't null")
    private String password;
    private RoleEnum role;
    private String city;
    private int postalCode;
    private String phoneNumber;
}
