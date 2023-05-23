package com.test.recipemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    public AuthenticationToken (User user) {
        this.token = UUID.randomUUID().toString();
        this.user = user;
    }
}
