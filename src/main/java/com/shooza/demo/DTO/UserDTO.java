package com.shooza.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String email;


    public UserDTO(int id, String email) {
        this.id = id;
        this.email = email;
    }
}
