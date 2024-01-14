package com.spring.project.hotelbooking.dto.reservation;

import java.time.LocalDate;
import java.util.Set;

public class CreateReservationDTO {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numAdults;
    private int numChildren;
    private boolean resConfirmation;
    private int userId;
    private Set<Integer> roomIds;

    //region getters
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isResConfirmation() {
        return resConfirmation;
    }

    public Set<Integer> getRoomIds() {
        return roomIds;
    }

    //endregion

    //region setters
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public void setResConfirmation(boolean resConfirmation) {
        this.resConfirmation = resConfirmation;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setRoomIds(Set<Integer> roomIds) {
        this.roomIds = roomIds;
    }
    //endregion
}
