package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.dtos.UserAdressConverterDto;
import com.example.librarymanagementsystem.model.entities.Adress;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UserAdressDtoConverter implements Converter<Adress , UserAdressConverterDto> {
    @Override
    public UserAdressConverterDto convert(MappingContext<Adress, UserAdressConverterDto> mappingContext) {
        return convert(mappingContext.getSource());
    }

    public UserAdressConverterDto convert(Adress adress) {
        UserAdressConverterDto dto = UserAdressConverterDto.builder()
                .city(adress.getCity())
                .postalCode(adress.getPostalCode())
                .build();
        return dto;
    }
}
