package com.shooza.demo.controllers;

import com.shooza.demo.DTO.AuthRequest;
import com.shooza.demo.repositories.UserRepository;
import com.shooza.demo.services.AuthService;
import com.shooza.demo.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthRequest authRequest){
        return authService.signIn(authRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(authService.getCurrentUser(principal));
    }
}
