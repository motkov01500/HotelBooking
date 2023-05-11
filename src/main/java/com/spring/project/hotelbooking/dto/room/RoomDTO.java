package com.spring.project.hotelbooking.dto.room;

import com.spring.project.hotelbooking.dto.FacilityDTO;
import com.spring.project.hotelbooking.dto.GuestReviewDTO;

import java.util.Set;

public class RoomDTO {

    private int roomNumber;
    private Set<GuestReviewDTO> guestReviews;
    private Set<FacilityDTO> facilities;
    private String description;
    private int numberOfPlaces;
    private int pricePerNight;

    //region getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public Set<GuestReviewDTO> getGuestReviews() {
        return guestReviews;
    }

    public Set<FacilityDTO> getFacilities() {
        return facilities;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }
    //endregion

    //region setters
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setGuestReviews(Set<GuestReviewDTO> guestReviews) {
        this.guestReviews = guestReviews;
    }

    public void setFacilities(Set<FacilityDTO> facilities) {
        this.facilities = facilities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    //endregion
}
