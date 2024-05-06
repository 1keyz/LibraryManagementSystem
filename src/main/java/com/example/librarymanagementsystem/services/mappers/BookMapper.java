package com.example.librarymanagementsystem.services.mappers;

import com.example.librarymanagementsystem.dtos.request.BookRequestDto;
import com.example.librarymanagementsystem.dtos.request.UpdateBookRequestDto;
import com.example.librarymanagementsystem.dtos.response.BookResponseDto;
import com.example.librarymanagementsystem.model.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper{
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book fromRequestToBook(BookRequestDto requestDto);
    BookResponseDto fromBookToBookResponseDto(Book book);

}
