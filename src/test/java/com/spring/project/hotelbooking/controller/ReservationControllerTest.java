package com.spring.project.hotelbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationCheckInDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationExtendDateDto;
import com.spring.project.hotelbooking.entity.*;
import com.spring.project.hotelbooking.util.DeleteDataFromDatabaseUtils;
import com.spring.project.hotelbooking.util.PersistDataUtils;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ReservationControllerTest {

    private final MockMvc mockMvc;
    private final PersistDataUtils persistDataUtils;
    private final ObjectMapper objectMapper;
    private final DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils;

    @Autowired
    public ReservationControllerTest(MockMvc mockMvc, PersistDataUtils persistDataUtils,
                                     ObjectMapper objectMapper, DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils) {
        this.mockMvc = mockMvc;
        this.persistDataUtils = persistDataUtils;
        this.objectMapper = objectMapper;
        this.deleteDataFromDatabaseUtils = deleteDataFromDatabaseUtils;
    }

    @AfterEach
    public void cleanDatabase() {
        deleteDataFromDatabaseUtils.cleanDatabase();
    }

    @Test
    public void testGetReservationsByUserEmail() throws Exception {
        //given
        List<Reservation> reservations = persistDataUtils.persistReservationOnCurrentDate();

        //when
        ResultActions response = mockMvc
                .perform(get("/reservations/getReservationsByEmail")
                .param("email","testtestov@gmail.com"));

        //then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$", hasSize(2)));
        response.andDo(print());
    }

    @Test
    public void testGetNotCheckedReservations() throws Exception {
        //given
        List<Reservation> reservation = persistDataUtils.persistReservationOnCurrentDate();

        //when
        ResultActions response = mockMvc.perform(get("/reservations/getAllNotCheckedInReservations"));

        //then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$",hasSize(2)));
        response.andExpect(jsonPath("$[0].resConfirmation").value("false"));
        response.andExpect(jsonPath("$[1].resConfirmation").value("false"));
        response.andDo(print());
    }

    @Test
    public void testCreateReservation() throws Exception {
        //given
        Loyalty loyalty = persistDataUtils.persistLoyalty("Golden",10);
        User user = persistDataUtils.persistUser("testestov@gmail.com",loyalty);
        Set<Room> rooms = persistDataUtils.persistRooms();
        Set<Integer> roomIds = rooms
                .stream()
                .map(Room::getId)
                .collect(Collectors.toSet());
        CreateReservationDTO reservation = TestDataUtils
                .createReservationForCreation(user,roomIds);
        byte[] contentToBytes = objectMapper
                .writeValueAsString(reservation)
                .getBytes(StandardCharsets.UTF_8);

        //when
        ResultActions response = mockMvc.perform(post("/reservations/createReservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentToBytes));

        //then
        response.andExpect(status().isCreated());
        response.andExpect(jsonPath("$.user.id").value(String.valueOf(reservation.getUserId())));
        response.andExpect(jsonPath("$.totalPrice").value(216));
    }

    @Test
    public void testExtendingReservation() throws Exception {
        //given
        Reservation reservation = persistDataUtils.persistReservation();
        int reservationId = reservation.getId();
        ReservationExtendDateDto reservationExtending = TestDataUtils
                .createReservationForExtending(reservation.getCheckOutDate().plusDays(2));
        byte[] contentToBytes = objectMapper
                .writeValueAsString(reservationExtending)
                .getBytes(StandardCharsets.UTF_8);

        //when
        ResultActions response = mockMvc.perform(patch("/reservations/extendReservation/" + reservationId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentToBytes));

        //then
        response.andExpect(status().isOk());
    }

    @Test
    public void testReservationCheckIn() throws Exception {
        //given
        Reservation reservation = persistDataUtils.persistReservation();
        Set<GuestInformation> guestInformations = persistDataUtils.persistGuestInformations();
        ReservationCheckInDTO reservationCheckInDTO = new ReservationCheckInDTO();
        reservationCheckInDTO.setResConfirmation(true);
        reservationCheckInDTO.setGuestInformation(guestInformations);
        int reservationID = reservation.getId();
        byte[] contentToBytes = objectMapper
                .writeValueAsString(reservationCheckInDTO)
                .getBytes(StandardCharsets.UTF_8);

        //when
        ResultActions response = mockMvc.perform(patch("/reservations/checkInReservation/" + reservationID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentToBytes));

        //then
        response.andExpect(status().isOk());
    }

    @Test
    public void testReservationDecline() throws Exception {
        //given
        Reservation reservations = persistDataUtils.persistReservation();
        int reservationId = reservations.getId();

        //when
        ResultActions response = mockMvc.perform(delete("/reservations/" + reservationId));

        //then
        response.andExpect(status().isNoContent());
    }
}