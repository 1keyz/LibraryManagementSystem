package com.example.librarymanagementsystem.services.abstracts;

import com.example.librarymanagementsystem.dtos.request.BookRequestDto;
import com.example.librarymanagementsystem.dtos.request.UpdateBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BookResponseDto;
import com.example.librarymanagementsystem.model.entities.Book;

import java.util.List;

public interface BookService {
    BookResponseDto addBook(BookRequestDto requestDto);
    void deleteBook(long id);
    BookResponseDto updateBook(long id, UpdateBookRequestDto requestDto);
    List<BookResponseDto> getAllBook();

    BookResponseDto getBookById(long id);
    Book getBookByIdForBorrow(long id);
    boolean bookIsBorrowed(long bookId);
    void bookHasCame(long bookId);
}
