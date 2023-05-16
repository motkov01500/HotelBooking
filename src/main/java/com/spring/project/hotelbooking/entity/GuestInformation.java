package com.spring.project.hotelbooking.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name= "guest_information")
public class GuestInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "phone_number", length = 10, unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "pin", length = 10, unique = true, nullable = false)
    private String pin;

    @Column(name = "age")
    private int age;

    @ManyToMany(mappedBy = "guestInformations")
    private Set<Reservation> reservations;

    //region getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPin() {
        return pin;
    }

    public int getAge() {
        return age;
    }

    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //endregion
}