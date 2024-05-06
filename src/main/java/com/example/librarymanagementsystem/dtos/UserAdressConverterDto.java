package com.example.librarymanagementsystem.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAdressConverterDto {
    private String city;
    private int postalCode;
}
