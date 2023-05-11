package com.spring.project.hotelbooking.xmlparser;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Room")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomTagXML {

    @XmlAttribute
    private int id;
    private int numberOfPlaces;
    private int pricePerNight;
    @XmlElement(name = "room_number")
    private int roomNumber;
    private String description;
    @XmlElement(name = "facilities")
    private FacilitiesTagXML facilities;

    public int getId() {
        return id;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public FacilitiesTagXML getFacilities() {
        return facilities;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFacilities(FacilitiesTagXML facilities) {
        this.facilities = facilities;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
