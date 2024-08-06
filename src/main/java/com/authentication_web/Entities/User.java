package com.authentication_web.Entities;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
    //@Table(name = "user_management")
    @Table(name = "user_management", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})

    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @Column(nullable = false, unique = true)
        @jakarta.validation.constraints.Email
        private String email;
        private String password;
        private String lastLoginTime;
        private String registrationTime;
        private boolean active = true;
        private String role;

        public User() {
        }


    public User(Long id, String name, String email, String password, String lastLoginTime, String registrationTime, boolean active, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.registrationTime = registrationTime;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", registrationTime=" + registrationTime +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
