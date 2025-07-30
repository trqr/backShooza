package com.shooza.demo.services;

import com.shooza.demo.DTO.RegisterRequest;
import com.shooza.demo.DTO.RegisterResponse;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponse registerUser(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new RegisterResponse(user.getFirstName(), user.getLastName(), user.getEmail(), "user registered successfully");
    }

}
