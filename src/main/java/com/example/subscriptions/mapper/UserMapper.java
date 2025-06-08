package com.example.subscriptions.mapper;

import com.example.subscriptions.dto.UserCreateDto;
import com.example.subscriptions.dto.UserDto;
import com.example.subscriptions.dto.UserUpdateDto;
import com.example.subscriptions.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    User fromCreateDto(UserCreateDto dto);

    void updateFromDto(UserUpdateDto dto, @MappingTarget User user);

}
