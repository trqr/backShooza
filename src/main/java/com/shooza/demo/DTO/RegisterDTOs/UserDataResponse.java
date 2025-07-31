package com.shooza.demo.DTO.RegisterDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataResponse {
    private String firstName;
    private String lastName;
    private String email;

    public UserDataResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDataResponse() {
    }

}
