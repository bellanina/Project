package com.example.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password; // Θα είναι κρυπτογραφημένο

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles; // Π.χ. ROLE_PROGRAMMER, ROLE_STAFF

    // Constructors, Getters, Setters
    public User() {}
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}