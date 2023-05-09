package com.spring.project.hotelbooking.controller;

import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationCheckInDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationExtendDateDto;
import com.spring.project.hotelbooking.entity.Reservation;
import com.spring.project.hotelbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("getReservationsByEmail")
    public ResponseEntity<List<ReservationDTO>> getReservationByUserEmail(@RequestParam("email") String email) {
        List<ReservationDTO> reservations = reservationService.getReservationsByUserEmail(email);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("getAllNotCheckedInReservations")
    public ResponseEntity<List<ReservationDTO>> getAllNotCheckedInReservations() {
        List<ReservationDTO> reservations = reservationService.getAllNotCheckedInReservations();
        return new ResponseEntity<>(reservations,HttpStatus.OK);
    }

    @PostMapping("createReservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PatchMapping("extendReservation/{id}")
    public ResponseEntity<Reservation> extendingReservation(@PathVariable int id, @RequestBody ReservationExtendDateDto updateDate) {
        Reservation updatedReservation = reservationService.extensionReservationTime(id, updateDate);
        return  new ResponseEntity<>(updatedReservation,HttpStatus.OK);
    }

    @PatchMapping("checkInReservation/{id}")
    public ResponseEntity<Reservation> checkInReservation(@PathVariable int id, @RequestBody ReservationCheckInDTO reservationCheckInDTO) {
        Reservation checkedReservation = reservationService.checkAndAddGuestsToReservation(id,reservationCheckInDTO);
        return new ResponseEntity<>(checkedReservation,HttpStatus.OK);
    }

    @DeleteMapping("deleteReservation")
    public void removeReservation(@RequestParam String bookingId) {
        reservationService.deleteReservation(bookingId);
    }
}