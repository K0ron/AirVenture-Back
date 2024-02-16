package com.airventure.airventureback.user.domain.dto;

import com.airventure.airventureback.user.domain.entity.Role;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isEnabled;
    private Role roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
