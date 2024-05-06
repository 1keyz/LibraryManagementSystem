package com.example.librarymanagementsystem.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BringBackBorrowedBookRequestDto {
    private long bookId;
    private long userId;
}
