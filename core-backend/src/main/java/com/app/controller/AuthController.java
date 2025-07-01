package com.app.controller;

import com.app.model.User;
import com.app.security.UserDetailsImpl;
import com.app.repository.UserRepository;
import com.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUserOpt = userRepository.findByUsername(user.getUsername());

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        User existingUser = existingUserOpt.get();

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok().body("Bearer " + token);
    }
}
