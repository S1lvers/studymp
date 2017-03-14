package com.studymp.persistance.entity;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}