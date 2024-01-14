package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.reservation.ReservationCheckInDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationExtendDateDto;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.entity.Reservation;
import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.mapper.ReservationMapper;
import com.spring.project.hotelbooking.repository.ReservationRepository;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;
    @Mock
    UserService userService;
    @Mock
    ReservationMapper reservationMapper;
    @Mock
    RoomService roomService;
    @InjectMocks
    ReservationService reservationService;
    User user;

    @BeforeEach
    public void setUp(){
        user = TestDataUtils.createTestUser();
    }

    @Test
    void getReservationsByUserEmail() {
        //given
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        Reservation secondReservation = TestDataUtils.createReservationOnFixedDate(user);
        List<Reservation> reservations = new ArrayList<>() {
            {
                add(reservation);
                add(secondReservation);
            }
        };

        //when
        when(reservationRepository.getReservationByUserEmail("testtesov@gmail.com"))
                .thenReturn(reservations);
        List<ReservationDTO> expectedReservations = reservationService.getReservationsByUserEmail("testtesov@gmail.com");

        //then
        Assertions.assertEquals(2,expectedReservations.size());
    }

    @Test
    public void testExtendingReservationTime() {
        //given
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        ReservationExtendDateDto reservationExtending = TestDataUtils.createReservationForExtending(LocalDate.of(2023,5,6));

        //when
        when(reservationRepository.save(reservation))
                .thenReturn(reservation);
        when(reservationRepository.findById(reservation.getId()))
                .thenReturn(Optional.of(reservation));
        Reservation newlyCreatedReservation =  reservationService.extensionReservationTime(reservation.getId(),reservationExtending);

        //then
        Assertions.assertEquals(newlyCreatedReservation,reservation);
    }

    @Test
    void getAllNotCheckedInReservations() {
        //given
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        Reservation secondreservation = TestDataUtils.createReservationOnFixedDate(user);
        reservations.add(reservation);
        reservations.add(secondreservation);

        //when
        when(reservationRepository.getAllNotCheckedInReservations())
                .thenReturn(reservations);
        List<Reservation> expectedReservations = reservationRepository.getAllNotCheckedInReservations();

        //then
        Assertions.assertEquals(expectedReservations,reservations);
    }

    @Test
    void checkAndAddGuestsToReservation() {
        //given
        Reservation reservation = TestDataUtils.createReservationOnFixedDate(user);
        Set<GuestInformation> guestInformations = new HashSet<>();
        GuestInformation guestInformation = TestDataUtils.createGuestInformation("0544258623","0926518486");
        GuestInformation secondGuestInformation = TestDataUtils.createGuestInformation("0843166251","0963954915");
        guestInformations.add(guestInformation);
        guestInformations.add(secondGuestInformation);
        ReservationCheckInDTO checkedReservation = new ReservationCheckInDTO();
        checkedReservation.setGuestInformation(guestInformations);

        //when
        when(reservationRepository.save(reservation))
                .thenReturn(reservation);
        when(reservationRepository.findById(reservation.getId()))
                .thenReturn(Optional.of(reservation));
        Reservation updatedReservation = reservationService.checkAndAddGuestsToReservation(reservation.getId(),checkedReservation);

        //then
        Assertions.assertEquals(updatedReservation,reservation);
    }
}