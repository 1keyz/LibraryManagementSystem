package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.dtos.response.UserResponseDto;
import com.example.librarymanagementsystem.model.entities.User;
import com.example.librarymanagementsystem.services.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDtoConverter implements Converter<User, UserResponseDto> {
    private UserAdressDtoConverter adressDtoConverter;
    @Override
    public UserResponseDto convert(MappingContext<User, UserResponseDto> mappingContext) {
        return convert(mappingContext.getSource());
    }

    public UserResponseDto convert(User user) {
        UserResponseDto responseDto = UserMapper.INSTANCE.fromUserToUserResponseDto(user);
        responseDto.setAdressDto(adressDtoConverter.convert(user.getUserAdress()));
        return responseDto;
    }
}
