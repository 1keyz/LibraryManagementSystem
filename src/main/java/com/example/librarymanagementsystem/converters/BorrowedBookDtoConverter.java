package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.dtos.response.BorrowedBookResponseDto;
import com.example.librarymanagementsystem.model.entities.BorrowedBook;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class BorrowedBookDtoConverter implements Converter<BorrowedBook , BorrowedBookResponseDto> {
    @Override
    public BorrowedBookResponseDto convert(MappingContext<BorrowedBook, BorrowedBookResponseDto> mappingContext) {
        return convert(mappingContext.getSource());
    }

    public BorrowedBookResponseDto convert(BorrowedBook borrowedBook) {
        BorrowedBookResponseDto responseDto = BorrowedBookResponseDto.builder()
                .bookName(borrowedBook.getBookName())
                .howManyDay(borrowedBook.getHowManyDay())
                .build();
        return responseDto;
    }
}
