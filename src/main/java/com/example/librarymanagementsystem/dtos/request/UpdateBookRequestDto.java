package com.example.librarymanagementsystem.dtos.request;

import lombok.Data;

@Data
public class UpdateBookRequestDto {
    private String bookName;
    private String autherName;
}
