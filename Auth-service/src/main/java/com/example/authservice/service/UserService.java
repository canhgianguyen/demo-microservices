package com.example.authservice.service;

import com.example.authservice.entity.User;
import com.example.authservice.exception.NotFoundException;
import com.example.authservice.locale.Translator;
import com.example.authservice.model.dto.UserDto;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUserName(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Translator.toLocale("error.msg.record.not_found")));
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepository
                .findByUsername(userDto.getUsername())
                .orElseThrow(() -> new NotFoundException(Translator.toLocale("error.msg.record.not_found")));
        user.setBalance(userDto.getBalance());
        userRepository.save(user);
        return UserConverter.convertUserToUserDto(user);
    }
}
