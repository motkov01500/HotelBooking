package com.spring.project.hotelbooking.dto;

import com.spring.project.hotelbooking.entity.UserRole;

public class UserDTO {

    private String email;
    private UserRoleDTO role;
    private LoyaltyDTO loyalty;

    //region getter
    public String getEmail() {
        return email;
    }

    public UserRoleDTO getRole() {
        return role;
    }

    public LoyaltyDTO getLoyalty() {
        return loyalty;
    }
    //endregion

    //region setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRoleDTO role) {
        this.role = role;
    }

    public void setLoyalty(LoyaltyDTO loyalty) {
        this.loyalty = loyalty;
    }
    //endregion
}
