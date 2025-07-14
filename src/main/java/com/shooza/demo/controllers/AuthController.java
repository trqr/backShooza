package com.shooza.demo.controllers;

import com.shooza.demo.DTO.AuthRequest;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.UserRepository;
import com.shooza.demo.services.AuthService;
import com.shooza.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        return authService.signIn(authRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token manquant ou invalide");
        }

        String token = authHeader.substring(7);
        String email;

        try {
            email = jwtUtil.extractUsername(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide");
        }

        Optional<User> userOptional = userRepository.findOptionalByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur introuvable");
        }

        User user = userOptional.get();
        return ResponseEntity.ok(user);
    }
}
