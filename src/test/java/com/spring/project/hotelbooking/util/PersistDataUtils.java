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

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final GuestInformationRepository guestInformationRepository;
    private final RoomRepository roomRepository;
    private final FacilityRepository facilityRepository;
    private final ReservationRepository reservationRepository;
    private final LoyaltyTypeRepository loyaltyTypeRepository;

    @Autowired
    public PersistDataUtils(UserRepository userRepository, UserRoleRepository userRoleRepository,
                            GuestInformationRepository guestInformationRepository, RoomRepository roomRepository,
                            FacilityRepository facilityRepository, ReservationRepository reservationRepository,
                            LoyaltyTypeRepository loyaltyTypeRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.guestInformationRepository = guestInformationRepository;
        this.roomRepository = roomRepository;
        this.facilityRepository = facilityRepository;
        this.reservationRepository = reservationRepository;
        this.loyaltyTypeRepository = loyaltyTypeRepository;
    }

    @Transactional
    public List<Reservation> persistReservationOnCurrentDate() {
        Loyalty loyalty = persistLoyalty("Golden", 10);
        User user = persistUser("testtestov@gmail.com", loyalty);
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
        Loyalty loyalty = persistLoyalty("Golden", 10);
        User user = persistUser("testtestov@gmail.com", loyalty);
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        reservationRepository.save(reservation);
        return reservation;
    }

    public User persistUser(String email, Loyalty loyalty) {
        UserRole role = persistUserRole("guest");
        User user = new User();
        user.setEmail(email);
        user.setLoyalty(loyalty);
        user.setPassword("test");
        user.setRole(role);
        return userRepository.save(user);
    }

    public User persistUserWithRole(String email, UserRole role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword("test");
        user.setRole(role);
        return userRepository.save(user);
    }

    public UserRole persistUserRole(String roleName) {
        UserRole role = new UserRole();
        role.setRoleName(roleName);
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

    public Loyalty persistLoyalty(String name, int discount) {
        Loyalty loyalty = TestDataUtils.createLoyalty(name,discount);
        loyaltyTypeRepository.save(loyalty);
        return loyalty;
    }

    public void cleanDatabase() {
        userRoleRepository.deleteAll();
        userRepository.deleteAll();
        reservationRepository.deleteAll();
        roomRepository.deleteAll();
        facilityRepository.deleteAll();
    }
}
