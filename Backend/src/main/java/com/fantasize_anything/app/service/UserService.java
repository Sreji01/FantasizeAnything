package com.fantasize_anything.app.service;

import com.fantasize_anything.app.domain.User;
import com.fantasize_anything.app.dto.LoginDTO;
import com.fantasize_anything.app.dto.UserResponseDTO;
import com.fantasize_anything.app.dto.RegisterDTO;
import com.fantasize_anything.app.repository.UserRepository;
import com.fantasize_anything.app.security.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }

    public UserResponseDTO registerUser(RegisterDTO dto) {
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

        userRepository.save(user);

        return new UserResponseDTO(null, user.getUsername(), user.getEmail());
    }

    public UserResponseDTO loginUser(LoginDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new UserResponseDTO(token, user.getUsername(), user.getEmail());
    }
}