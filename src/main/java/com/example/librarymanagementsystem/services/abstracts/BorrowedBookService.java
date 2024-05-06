package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.dtos.request.BorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.request.BringBackBorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BorrowedBookResponseDto;
import com.example.librarymanagementsystem.dtos.response.BringBackBorrewedBookResponseDto;
import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import com.example.librarymanagementsystem.model.entities.User;

import java.util.List;

public interface BorrowedBookService {
    BorrowedBookResponseDto borrowingBook(BorrowedBookRequestDto requestDto);

    BringBackBorrewedBookResponseDto bringBackTheBorrewedBook(BringBackBorrowedBookRequestDto requestDto);

    List<BorrowedBook> BringTimePassed();
}
