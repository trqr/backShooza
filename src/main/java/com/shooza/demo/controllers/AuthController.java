package com.shooza.demo.controllers;

import com.shooza.demo.DTO.AuthRequest;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.UserRepository;
import com.shooza.demo.services.AuthService;
import com.shooza.demo.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthRequest authRequest){
        return authService.signIn(authRequest);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findOptionalByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable"));
        return ResponseEntity.ok(user);
    }
}
