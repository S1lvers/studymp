package com.studymp.persistence.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by qwerty on 15.03.2017.
 */
@Entity
@Table(schema = "marketplace")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }
}