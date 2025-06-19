package com.shooza.demo.controllers;

import com.shooza.demo.models.User;
import com.shooza.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam User user){
        userService.registerUser(user);
        return "user registered !";
    }
}
