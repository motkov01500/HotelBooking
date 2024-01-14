package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.entity.Reservation;
import com.spring.project.hotelbooking.entity.Room;
import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {

    ReservationMapper reservationMapper;

    @BeforeEach
    public void setUp() {
        reservationMapper = new ReservationMapperImpl();
    }

    @Test
    public void reservationToReservationDTO() {
        //given
        User user = TestDataUtils.createTestUser();
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        Set<GuestInformation> guestInformations = new HashSet<>();
        GuestInformation guestInformation = TestDataUtils.createGuestInformation("0544258623","0926518486");
        GuestInformation secondGuestInformation = TestDataUtils.createGuestInformation("0843166251","0963954915");
        guestInformations.add(guestInformation);
        guestInformations.add(secondGuestInformation);
        reservation.setGuestInformations(guestInformations);

        //when
        ReservationDTO reservationDTO = reservationMapper.reservationToReservationDTO(reservation);

        //then

        Assertions.assertEquals(reservation.getCheckInDate(),reservationDTO.getCheckInDate());
        Assertions.assertEquals(reservation.getCheckOutDate(),reservationDTO.getCheckOutDate());
        Assertions.assertEquals(reservation.isResConfirmation(),reservationDTO.isResConfirmation());
    }

    @Test
    public void createReservationToReservation() {
        //given
        Room room = TestDataUtils.createRoom();
        Set<Integer> roomIds = new HashSet<>();
        Set<Room> rooms = new HashSet<>();
        rooms.add(room);
        roomIds.add(room.getId());
        User user = TestDataUtils.createTestUser();
        CreateReservationDTO createReservationDTO = TestDataUtils.createReservationForCreation(user,roomIds);

        //when
        Reservation reservation = reservationMapper.createReservationToReservation(createReservationDTO);

        //then
        Assertions.assertEquals(createReservationDTO.getCheckInDate(),reservation.getCheckInDate());
        Assertions.assertEquals(createReservationDTO.getCheckOutDate(),reservation.getCheckOutDate());
        Assertions.assertEquals(createReservationDTO.isResConfirmation(),reservation.isResConfirmation());
    }

    @Test
    public void afterMapping() {
        //given
        ReservationDTO reservationDTO = new ReservationDTO();
        GuestInformationDTO guestInformationDTO = TestDataUtils.createGuestInformationDTO();
        Set<GuestInformationDTO> guestInformationDTOS = new HashSet<>();
        guestInformationDTOS.add(guestInformationDTO);
        reservationDTO.setGuestInformations(guestInformationDTOS);
        GuestInformationDTO firstElementOfReservationGuests = reservationDTO.getGuestInformations().iterator().next();

        //when
        reservationMapper.afterMapping(reservationDTO);

        //then

        Assertions.assertEquals(firstElementOfReservationGuests.getFullName(),firstElementOfReservationGuests.getFirstName()+" " + firstElementOfReservationGuests.getLastName());
    }
}