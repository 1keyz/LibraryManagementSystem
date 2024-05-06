package com.example.librarymanagementsystem.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class BorrowedBookRequestDto {
    private long bookId;
    @NotBlank(message = "you have to give the how many days to use !")
    private int howManyDay;
}
