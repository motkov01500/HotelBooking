package com.spring.project.hotelbooking.dto.reservation;

import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.dto.room.RoomDTO;

import java.time.LocalDate;
import java.util.Set;

public class ReservationDTO {

    private int id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean resConfirmation;
    private Set<RoomDTO> rooms;
    private Set<GuestInformationDTO> guestInformations;
    private UserDTO user;
    private int totalPrice;

    //region getters
    public int getId() {
        return id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public Set<RoomDTO> getRooms() {
        return rooms;
    }

    public boolean isResConfirmation() {
        return resConfirmation;
    }

    public Set<GuestInformationDTO> getGuestInformations() {
        return guestInformations;
    }

    public UserDTO getUser() {
        return user;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRooms(Set<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public void setGuestInformations(Set<GuestInformationDTO> guestInformations) {
        this.guestInformations = guestInformations;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setResConfirmation(boolean resConfirmation) {
        this.resConfirmation = resConfirmation;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    //endregion
}
