package com.tracer85.shop.model.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 3521136866216360514L;
    private long id;
    private String UserId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
}
