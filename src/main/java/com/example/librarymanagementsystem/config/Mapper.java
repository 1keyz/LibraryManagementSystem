package com.example.librarymanagementsystem.config;

import com.example.librarymanagementsystem.converters.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class Mapper {
    private UserAdressDtoConverter userAdressDtoConverter;
    @Bean
    public org.modelmapper.ModelMapper modelMapper(){
        org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();
        modelMapper.addConverter(new BookDtoConverter());
        modelMapper.addConverter(new UserDtoConverter(userAdressDtoConverter));
        modelMapper.addConverter(new BorrowedBookDtoConverter());
        modelMapper.addConverter(new UserAdressDtoConverter());
        return modelMapper;
    }
}
