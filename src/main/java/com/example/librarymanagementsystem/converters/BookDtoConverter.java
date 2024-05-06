package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.dtos.response.BookResponseDto;
import com.example.librarymanagementsystem.model.entities.Book;
import com.example.librarymanagementsystem.services.mappers.BookMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter implements Converter<Book, BookResponseDto> {
    @Override
    public BookResponseDto convert(MappingContext<Book, BookResponseDto> mappingContext) {
        return convert(mappingContext.getSource());
    }

    public BookResponseDto convert(Book book) {
        BookResponseDto responseDto = BookMapper.INSTANCE.fromBookToBookResponseDto(book);
        return responseDto;
    }
}
