package com.example.subscriptions.service;

import com.example.subscriptions.dto.UserCreateDto;
import com.example.subscriptions.dto.UserDto;
import com.example.subscriptions.dto.UserUpdateDto;
import com.example.subscriptions.model.User;

public interface UserService {

    UserDto createUser(UserCreateDto dto);

    UserDto getUser(long userId);

    UserDto updateUser(long userId, UserUpdateDto dto);

    void deleteUser(long id);
}
