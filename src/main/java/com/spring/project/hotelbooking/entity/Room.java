package com.spring.project.hotelbooking.entity;

import javax.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "number_of_places", nullable = false)
    private int numberOfPlaces;

    @Column(name = "price_per_night", nullable = false)
    private double pricePerNight;

    @Column(name = "description")
    private String description;

    @Column(name = "room_number")
    private int roomNumber;

    @ManyToMany(mappedBy = "rooms")
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "room_facility",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "facility_id")})
    private Set<Facility> facilities;

    @ManyToMany
    @JoinTable(name = "room_image",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Set<Image> images;

    @OneToMany(targetEntity = GuestReview.class)
    @JoinColumn(name = "room_id")
    private Set<GuestReview> guestReviews;

    //region getters
    public int getId() {
        return id;
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

    public Set<Facility> getFacilities() {
        return Collections.unmodifiableSet(facilities);
    }

    public Set<GuestReview> getGuestReviews() {
        return guestReviews;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    //endregion

    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        if(numberOfPlaces < 1) {
            return;
        }
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setPricePerNight(double pricePerNight) {
        if(pricePerNight <= 0) {
            return;
        }
        this.pricePerNight = pricePerNight;
    }

    public void setDescription(String description) {
        if(description.isEmpty()) {
            return;
        }
        this.description = description;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void setGuestReviews(Set<GuestReview> guestReviews) {
        this.guestReviews = guestReviews;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //endregion
}
