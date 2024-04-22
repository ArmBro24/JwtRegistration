package com.example.javaregistration.entity;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"users\"")
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id", "email"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

}