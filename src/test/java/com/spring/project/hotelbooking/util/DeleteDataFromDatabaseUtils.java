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

    @Autowired
    public DeleteDataFromDatabaseUtils(UserRepository userRepository, UserRoleRepository userRoleRepository, GuestInformationRepository guestInformationRepository, RoomRepository roomRepository, FacilityRepository facilityRepository, GuestReviewRepository guestReviewRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.guestInformationRepository = guestInformationRepository;
        this.roomRepository = roomRepository;
        this.facilityRepository = facilityRepository;
        this.guestReviewRepository = guestReviewRepository;
    }

    public void cleanDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        guestReviewRepository.deleteAll();
        roomRepository.deleteAll();
        facilityRepository.deleteAll();
        guestReviewRepository.deleteAll();
    }
}
