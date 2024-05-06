package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.request.BorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.request.BringBackBorrowedBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BorrowedBookResponseDto;
import com.example.librarymanagementsystem.dtos.response.BringBackBorrewedBookResponseDto;
import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import com.example.librarymanagementsystem.services.abstracts.BorrowedBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrowed-book")
@AllArgsConstructor
public class BorrowedBookController {
    private BorrowedBookService borrowedBookService;

    @PostMapping("/borrowing")
    public ResponseEntity<BorrowedBookResponseDto> borrowingBook(@RequestBody BorrowedBookRequestDto requestDto){
        return ResponseEntity.ok(borrowedBookService.borrowingBook(requestDto));
    }

    @PostMapping("/bring-back")
    public ResponseEntity<BringBackBorrewedBookResponseDto> bringBackTheBorrewedBook(@RequestBody BringBackBorrowedBookRequestDto requestDto) {
        return ResponseEntity.ok(borrowedBookService.bringBackTheBorrewedBook(requestDto));
    }

}
