package com.spring.project.hotelbooking.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "facilities")
    private Set<Room> rooms = new HashSet<>();

    //region getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
