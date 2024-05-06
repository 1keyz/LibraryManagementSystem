package com.example.librarymanagementsystem.dtos.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowedBookResponseDto {
    private String bookName;
    private int howManyDay;
}
