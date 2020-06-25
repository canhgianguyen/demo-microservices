package com.example.authservice.utils;

import com.example.authservice.entity.User;
import com.example.authservice.model.dto.UserDto;

public class UserConverter {
    public static UserDto convertUserToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .balance(user.getBalance())
                .role(user.getRole())
                .build();
        return userDto;
    }
}
