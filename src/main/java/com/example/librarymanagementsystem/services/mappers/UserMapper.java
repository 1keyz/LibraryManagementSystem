package com.example.librarymanagementsystem.services.mappers;

import com.example.librarymanagementsystem.dtos.request.UserRequestDto;
import com.example.librarymanagementsystem.dtos.response.UserResponseDto;
import com.example.librarymanagementsystem.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User fromUserRequestDtoToUser(UserRequestDto requestDto);

    UserResponseDto fromUserToUserResponseDto(User user);

}
