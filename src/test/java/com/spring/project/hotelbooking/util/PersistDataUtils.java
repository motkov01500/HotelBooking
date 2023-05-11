package com.spring.project.hotelbooking.util;

import com.spring.project.hotelbooking.entity.*;
import com.spring.project.hotelbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersistDataUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private GuestInformationRepository guestInformationRepository;
    private RoomRepository roomRepository;
    private FacilityRepository facilityRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public PersistDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, GuestInformationRepository guestInformationRepository, RoomRepository roomRepository, FacilityRepository facilityRepository, GuestReviewRepository guestReviewRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.guestInformationRepository = guestInformationRepository;
        this.roomRepository = roomRepository;
        this.facilityRepository = facilityRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public List<Reservation> persistReservationOnCurrentDate() {
        User user = persistUser("testtestov@gmail.com");
        Set<GuestInformation> guestInformations = persistGuestInformations();
        Set<Room> rooms = persistRooms();
        Reservation reservation = TestDataUtils.createNormalReservation(user,false,guestInformations,rooms);
        Reservation secondReservation = TestDataUtils.createNormalReservation(user,false,guestInformations,rooms);
        reservationRepository.save(reservation);
        reservationRepository.save(secondReservation);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        reservations.add(secondReservation);
        return reservations;
    }

    public Reservation persistReservation() {
        User user = persistUser("testtestov@gmail.com");
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        reservationRepository.save(reservation);
        return reservation;
    }

    public User persistUser(String email) {
        UserRole role = persistUserRole();
        User user = new User();
        user.setEmail(email);
        user.setPassword("test");
        user.setRole(role);
        return userRepository.save(user);
    }

    public UserRole persistUserRole() {
        UserRole role = new UserRole();
        role.setRoleName("guest");
        return userRoleRepository.save(role);
    }

    public Set<GuestInformation> persistGuestInformations() {
        GuestInformation guestInformation = TestDataUtils.createGuestInformation("0544258623","0926518486");
        GuestInformation secondGuestInformation = TestDataUtils.createGuestInformation("0843166251","0963954915");
        guestInformationRepository.save(guestInformation);
        guestInformationRepository.save(secondGuestInformation);
        Set<GuestInformation> guestInformations = new HashSet<>();
        guestInformations.add(guestInformation);
        guestInformations.add(secondGuestInformation);
        return guestInformations;
    }

    public Set<Room> persistRooms() {
        Room roomOne = TestDataUtils.createRoom();
        Room roomTwo = TestDataUtils.createRoom();
        facilityRepository.save(roomOne.getFacilities().iterator().next());
        facilityRepository.save(roomTwo.getFacilities().iterator().next());
        roomRepository.save(roomOne);
        roomRepository.save(roomTwo);
        Set<Room> rooms = new HashSet<>();
        rooms.add(roomOne);
        rooms.add(roomTwo);
        return rooms;
    }

    public Set<Facility> persistFacilities() {
        Facility facility = TestDataUtils.createFacility("Parking");
        Facility secondFacility = TestDataUtils.createFacility("Fitness");
        facilityRepository.save(facility);
        facilityRepository.save(secondFacility);
        Set<Facility> facilities = new HashSet<>();
        facilities.add(facility);
        facilities.add(secondFacility);
        return facilities;
    }

    public void cleanDatabase() {
        reservationRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        roomRepository.deleteAll();
        facilityRepository.deleteAll();
    }
}
