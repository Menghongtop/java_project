package com.assignment.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")   // matches SQL column
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_type_id", nullable = false)
    private Integer userTypeId;  // FK to users_type

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "registration_date", updatable = false, insertable = false)
    private java.sql.Timestamp registrationDate;

    // --- Getters and Setters ---
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getUserTypeId() {
        return userTypeId;
    }
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public java.sql.Timestamp getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(java.sql.Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}