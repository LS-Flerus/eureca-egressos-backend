package com.eureca.egressos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "enrollment")
    private String enrollment;

    @Column(name = "courseCode")
    private String courseCode;

    @Column(name = "password")
    private String password;

    @Column(name = "idPlaque")
    private int idPlaque;
}
