package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.request.BookRequestDto;
import com.example.librarymanagementsystem.dtos.request.UpdateBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BookResponseDto;
import com.example.librarymanagementsystem.services.abstracts.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
@Slf4j
public class BookController {
    private BookService bookService;

    @PostMapping("/add-book")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto requestDto){
        return ResponseEntity.ok(bookService.addBook(requestDto));
    }

    @DeleteMapping("/{id]")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        log.info("Book has deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable long id, @RequestBody UpdateBookRequestDto requestDto) {
       return ResponseEntity.ok(bookService.updateBook(id,requestDto));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BookResponseDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable long bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }
}
