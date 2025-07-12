package com.shooza.demo.services;

import com.shooza.demo.DTO.AuthRequest;
import com.shooza.demo.DTO.AuthResponse;
import com.shooza.demo.DTO.UserDTO;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.UserRepository;
import com.shooza.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> signIn(AuthRequest authRequest){
        User currentUser = userRepository.findByEmail(authRequest.getEmail());
        if (currentUser != null && passwordEncoder.matches(authRequest.getPassword(), currentUser.getPassword())) {
            String token = jwtUtil.generateToken(currentUser.getEmail());

            UserDTO userDTO = new UserDTO(
                    currentUser.getId(),
                    currentUser.getEmail(),
                    currentUser.getRole()
            );

            AuthResponse response = new AuthResponse(token, userDTO);
            return ResponseEntity.ok(response);
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong email or password.");
    }

}
