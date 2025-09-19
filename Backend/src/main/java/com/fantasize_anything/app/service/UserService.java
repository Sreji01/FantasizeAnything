package com.fantasize_anything.app.service;

import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.UserDTO;
import com.fantasize_anything.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username already in use");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }
}
