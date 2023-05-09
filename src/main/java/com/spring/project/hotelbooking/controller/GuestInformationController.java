package com.spring.project.hotelbooking.controller;

import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.service.GuestInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guestinformation")
public class GuestInformationController {

    private final GuestInformationService guestInformationService;

    @Autowired
    public GuestInformationController(GuestInformationService guestInformationService) {
        this.guestInformationService = guestInformationService;
    }

    @GetMapping("guestsInRoom")
    public ResponseEntity<List<GuestInformationDTO>> getGuestsInRoom(@RequestParam("roomId") int roomId) {
        List<GuestInformationDTO> guestInformations = guestInformationService.getAllGuestsInRoom(roomId);
        return new ResponseEntity<>(guestInformations, HttpStatus.OK);
    }

    @GetMapping("allGuests")
    public ResponseEntity<List<GuestInformationDTO>> getAllGuests() {
        List<GuestInformationDTO> guestInformations = guestInformationService.getAllGuests();
        return new ResponseEntity<>(guestInformations, HttpStatus.OK);
    }
}
