package com.spring.project.hotelbooking.dto;

public class CreateGuestInformationDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String pin;
    private String age;

    //region getters
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

    public String getAge() {
        return age;
    }
    //endregion

    //region setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setAge(String age) {
        this.age = age;
    }
    //endregion
}
