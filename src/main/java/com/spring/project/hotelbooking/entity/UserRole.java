package com.spring.project.hotelbooking.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    //region getters
    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    //endregion
}