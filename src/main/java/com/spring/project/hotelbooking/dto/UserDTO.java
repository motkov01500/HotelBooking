package com.spring.project.hotelbooking.dto;

import com.spring.project.hotelbooking.entity.UserRole;

public class UserDTO {

    private String email;
    private UserRole role;

    //region getter
    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
    //endregion

    //region setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    //endregion
}
