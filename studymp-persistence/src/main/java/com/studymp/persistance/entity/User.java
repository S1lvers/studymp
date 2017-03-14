package com.studymp.persistance.entity;

import javax.persistence.*;

/**
 * Created by qwerty on 14.03.2017.
 */
@Entity
@Table(name = "user", schema = "marketplace")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
