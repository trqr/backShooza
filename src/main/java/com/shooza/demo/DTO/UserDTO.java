package com.shooza.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String email;
    private String role;


    public UserDTO(int id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
