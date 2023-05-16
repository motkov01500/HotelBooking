package com.spring.project.hotelbooking.util;

import com.spring.project.hotelbooking.dto.CreateGuestInformationDTO;
import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationExtendDateDto;
import com.spring.project.hotelbooking.entity.*;
import com.spring.project.hotelbooking.service.LoyaltyService;
import com.spring.project.hotelbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestDataUtils {

    public static UserRole createUserRole(String name) {
        UserRole userRole = new UserRole();
        userRole.setRoleName(name);
        return userRole;
    }

    public static User createTestUser() {
        UserRole role = createUserRole("guest");
        User user = new User();
        user.setId(1);
        user.setEmail("testtestov@gmail.com");
        user.setPassword("test");
        user.setRole(role);
        return user;
    }

    public static Facility createFacility(String name) {
        Facility facility = new Facility();
        facility.setName(name);
        return facility;
    }

    public static Loyalty createLoyalty(String name, int discount) {
        Loyalty loyalty = new Loyalty();
        loyalty.setName(name);
        loyalty.setDiscount(discount);
        return loyalty;
    }

    public static Room createRoom() {
        Facility parking = createFacility("parking");
        Set<Facility> facilities = new HashSet<>();
        facilities.add(parking);
        Room room = new Room();
        room.setNumberOfPlaces(5);
        room.setPricePerNight(120);
        room.setDescription("Test description.");
        room.setFacilities(facilities);
        return room;
    }

    public static Room createRoomWithGivenId(int id) {
        Facility parking = createFacility("parking");
        Facility fitness = createFacility("fitness");
        Set<Facility> facilities = new HashSet<>();
        facilities.add(parking);
        facilities.add(fitness);
        Room room = new Room();
        room.setId(id);
        room.setNumberOfPlaces(5);
        room.setPricePerNight(120);
        room.setDescription("Test description.");
        room.setFacilities(facilities);
        return room;
    }

    public static GuestInformation createGuestInformation(String pin, String phoneNumber) {
        GuestInformation guestInformation = new GuestInformation();
        guestInformation.setPin(pin);
        guestInformation.setPhoneNumber(phoneNumber);
        guestInformation.setFirstName("Test");
        guestInformation.setLastName("Testov");
        guestInformation.setAge(23);
        return guestInformation;
    }

    public static GuestInformationDTO createGuestInformationDTO() {
        GuestInformationDTO guestInformationDTO = new GuestInformationDTO();
        guestInformationDTO.setFirstName("Test");
        guestInformationDTO.setLastName("Testov");
        return guestInformationDTO;
    }

    public static CreateGuestInformationDTO createCreateGuestInformationDTO() {
        CreateGuestInformationDTO createGuestInformationDTO = new CreateGuestInformationDTO();
        createGuestInformationDTO.setFirstName("Test");
        createGuestInformationDTO.setLastName("Testov");
        createGuestInformationDTO.setPin("1234567890");
        createGuestInformationDTO.setPhoneNumber("0889898989");
        return  createGuestInformationDTO;
    }

    public static Reservation createNormalReservation(User user, boolean resConfirmation, Set<GuestInformation> guestInformations, Set<Room> rooms) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setResConfirmation(resConfirmation);
        reservation.setCheckInDate(LocalDate.now().minusDays(1));
        reservation.setCheckOutDate(LocalDate.now().plusDays(1));
        reservation.setNumAdults(3);
        reservation.setNumChildren(2);
        reservation.setBookingId(UUID.randomUUID().toString());
        reservation.setGuestInformations(guestInformations);
        reservation.setRooms(rooms);
        double priceOfRooms = 0;
        for(Room room: rooms) {
            priceOfRooms+=room.getPricePerNight();
        }
        reservation.setTotalPrice(calculateTotalPriceOfRooms(priceOfRooms,user));
        return reservation;
    }

    public static Reservation createReservationOnFixedDate(User user) {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setUser(user);
        reservation.setResConfirmation(false);
        reservation.setCheckInDate(LocalDate.of(2023, 04, 20));
        reservation.setCheckOutDate(LocalDate.of(2023, 04, 22));
        reservation.setNumAdults(3);
        reservation.setNumChildren(2);
        return reservation;
    }

    public static CreateReservationDTO createReservationForCreation(User user, Set<Integer> roomsId) {
        CreateReservationDTO reservationDTO = new CreateReservationDTO();
        reservationDTO.setCheckInDate(LocalDate.now().minusDays(1));
        reservationDTO.setCheckOutDate(LocalDate.now().plusDays(1));
        reservationDTO.setNumAdults(3);
        reservationDTO.setNumChildren(1);
        reservationDTO.setResConfirmation(false);
        reservationDTO.setUserId(user.getId());
        reservationDTO.setRoomIds(roomsId);
        return reservationDTO;
    }

    public static ReservationExtendDateDto createReservationForExtending(LocalDate date) {
        ReservationExtendDateDto reservationExtendDateDto = new ReservationExtendDateDto();
        reservationExtendDateDto.setCheckInDate(date);
        return reservationExtendDateDto;
    }

    private static double calculateTotalPriceOfRooms(double priceOfRooms, User user) {
        int discount = user.getLoyalty().getDiscount();
        return priceOfRooms - ((priceOfRooms * discount)) / 100;
    }

    private double calculatePriceOfRooms(Set<Room> rooms) {
        double sum = 0;
        for (Room room: rooms) {
            sum += room.getPricePerNight();
        }
        return sum;
    }
}
