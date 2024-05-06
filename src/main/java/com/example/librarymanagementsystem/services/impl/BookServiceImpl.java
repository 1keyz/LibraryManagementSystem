package com.example.librarymanagementsystem.services.impl;

import com.example.librarymanagementsystem.core.advice.types.NotFoundException;
import com.example.librarymanagementsystem.dtos.request.BookRequestDto;
import com.example.librarymanagementsystem.dtos.request.UpdateBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BookResponseDto;
import com.example.librarymanagementsystem.model.entities.Book;
import com.example.librarymanagementsystem.repositories.BookRepository;
import com.example.librarymanagementsystem.services.abstracts.BookService;
import com.example.librarymanagementsystem.services.mappers.BookMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    @Override
    public BookResponseDto addBook(BookRequestDto requestDto) {
        Book book = BookMapper.INSTANCE.fromRequestToBook(requestDto);
        return modelMapper.map(bookRepository.save(book),BookResponseDto.class);
    }

    @Override
    public void deleteBook(long id) {
        Book book = getBookByIdForBorrow(id);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.delete(book);
    }

    @Override
    public BookResponseDto updateBook(long id, UpdateBookRequestDto requestDto) {
        Book book = getBookByIdForBorrow(id);
        book.setBookName(requestDto.getBookName());
        book.setAutherName(requestDto.getAutherName());
        book.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(bookRepository.save(book),BookResponseDto.class);
    }

    @Override
    public List<BookResponseDto> getAllBook() {
        List<BookResponseDto> responseDtoList = bookRepository.
                findAll().stream().map(x
                        -> modelMapper.map(x,BookResponseDto.class))
                .collect(Collectors.toList());
        return responseDtoList;
    }

    @Override
    public BookResponseDto getBookById(long id) {
        return modelMapper.map(getBookByIdForBorrow(id),BookResponseDto.class);
    }


    public Book getBookByIdForBorrow(long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not found"));
        book.setBorrowed(true);
        return book;
    }


    public boolean bookIsBorrowed(long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("book not found"));
        if (book.isBorrowed()){
            return true;
        }
        return false;
    }

    public void bookHasCame(long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new NotFoundException("book not found"));
        book.setBorrowed(false);
        bookRepository.save(book);
    }

}
