package com.example.MoneyManage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "setup", nullable = false)
    private Boolean setup;

    @Column(name = "password", nullable = false)
    private String password;
}

