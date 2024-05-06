package com.example.librarymanagementsystem.dtos.response;

import com.example.librarymanagementsystem.model.enums.BookSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class BookResponseDto {
    private String bookName;
    private String AutherName;
    private BookSubject bookSubject;
}
