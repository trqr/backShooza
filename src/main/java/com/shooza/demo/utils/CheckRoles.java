package com.shooza.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CheckRoles {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean isAdmin(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String role = jwtUtil.extractRole(token);
        return "ADMIN".equals(role);

    }

    public boolean isUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authHeader.substring(7);
        String role = jwtUtil.extractRole(token);
        return ("ADMIN".equals(role) || "USER".equals(role));
    }

}
