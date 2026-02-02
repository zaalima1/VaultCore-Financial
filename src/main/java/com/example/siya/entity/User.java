package com.example.siya.entity;


import org.hibernate.annotations.Generated;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(mappedBy = "user")
    private Account account;
}
