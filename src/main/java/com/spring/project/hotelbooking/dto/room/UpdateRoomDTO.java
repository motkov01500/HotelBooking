package com.spring.project.hotelbooking.dto.room;

public class UpdateRoomDTO {

    private int room_number;
    private int numberOfPlaces;
    private double pricePerNight;
    private String description;

    //region getters
    public int getRoom_number() {
        return room_number;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    //region setters
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion
}
