package com.example.librarymanagementsystem.dtos.request;

import com.example.librarymanagementsystem.model.enums.BookSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    private String bookName;
    private String autherName;
    private BookSubject bookSubject;
    private int numberOfPages;
}
