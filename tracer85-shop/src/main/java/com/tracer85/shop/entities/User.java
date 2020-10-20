package com.tracer85.shop.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 295769296723044214L;
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length=50)
    private String UserId;

    @Column(nullable = false, length=50)
    private String firstName;

    @Column(nullable = false, length=50)
    private String lastName;

    @Column(nullable = false, length=100)
    private String email;

    @Column(nullable = false, length=50)
    private String password;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus;




}
