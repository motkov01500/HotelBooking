package com.spring.project.hotelbooking.util;

import com.spring.project.hotelbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDataFromDatabaseUtils {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private GuestInformationRepository guestInformationRepository;
    private RoomRepository roomRepository;
    private FacilityRepository facilityRepository;
    private GuestReviewRepository guestReviewRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public DeleteDataFromDatabaseUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, GuestInformationRepository guestInformationRepository,
                                       RoomRepository roomRepository, FacilityRepository facilityRepository,
                                       GuestReviewRepository guestReviewRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.guestInformationRepository = guestInformationRepository;
        this.roomRepository = roomRepository;
        this.facilityRepository = facilityRepository;
        this.guestReviewRepository = guestReviewRepository;
        this.reservationRepository = reservationRepository;
    }

    public void cleanDatabase() {
        reservationRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        guestInformationRepository.deleteAll();
        roomRepository.deleteAll();
        facilityRepository.deleteAll();
        guestReviewRepository.deleteAll();
    }
}
