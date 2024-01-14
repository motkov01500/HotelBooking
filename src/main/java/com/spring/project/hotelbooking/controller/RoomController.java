package com.spring.project.hotelbooking.controller;

import com.spring.project.hotelbooking.dto.room.RoomDTO;
import com.spring.project.hotelbooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Scheduled(cron = "0 0 10 ? * *")
    @PatchMapping("updateRooms")
    public ResponseEntity<List<RoomDTO>> updateRooms() throws JAXBException {
        File xmlFile = new File("roomUpdate.xml");
        List<RoomDTO> updatedRooms = roomService.updateRooms(xmlFile);
        return new ResponseEntity<>(updatedRooms, HttpStatus.OK);
    }
}
