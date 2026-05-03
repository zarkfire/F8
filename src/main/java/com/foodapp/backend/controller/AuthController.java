package com.foodapp.backend.controller;

import com.foodapp.backend.model.User;
import com.foodapp.backend.repository.UserRepository;
import com.foodapp.backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> existing = Optional.ofNullable(
                userRepository.findByEmail(user.getEmail())
        );

        if (existing.isPresent() &&
                passwordEncoder.matches(user.getPassword(), existing.get().getPassword())) {

            return jwtService.generateToken(user.getEmail());
        }

        throw new RuntimeException("Invalid credentials");
    }
}