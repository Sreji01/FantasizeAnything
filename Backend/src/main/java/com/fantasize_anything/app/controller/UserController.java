package com.fantasize_anything.app.controller;

import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.LoginDTO;
import com.fantasize_anything.app.dto.UserResponseDTO;
import com.fantasize_anything.app.dto.RegisterDTO;
import com.fantasize_anything.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody RegisterDTO dto){
        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginDTO dto){
        return userService.loginUser(dto);
    }
}


