package com.example.authservice.controller;

import com.example.authservice.model.dto.UserDto;
import com.example.authservice.model.response.ResponseFactory;
import com.example.authservice.service.UserService;
import com.example.authservice.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private ResponseFactory responseFactory;

    @GetMapping("/{username}")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username) {
        return responseFactory.success(UserConverter.convertUserToUserDto(userService.findByUserName(username)));
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        return responseFactory.success(userService.updateUser(userDto));
    }
}
