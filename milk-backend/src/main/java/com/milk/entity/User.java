package com.milk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user") // good naming practice
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true) // username must be unique
    private String username;

    @Column(nullable = false)
    private String password; // will store encrypted value later

    @Column(nullable = false)
    private String role; // ADMIN / EMPLOYEE

    // Default constructor (required by JPA)
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
