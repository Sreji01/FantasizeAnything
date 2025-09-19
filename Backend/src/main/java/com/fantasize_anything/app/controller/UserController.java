package com.fantasize_anything.app.controller;

import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.UserDTO;
import com.fantasize_anything.app.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> register(@RequestBody UserDTO dto){
        User user = userService.registerUser(dto);
        return ResponseEntity.ok(user);
    }
}
