package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.mapper.GuestInformationMapper;
import com.spring.project.hotelbooking.repository.GuestInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestInformationService {

    private final GuestInformationRepository guestInformationRepository;
    private final GuestInformationMapper guestInformationMapper;

    @Autowired
    public GuestInformationService(GuestInformationRepository guestInformationRepository,
                                   GuestInformationMapper guestInformationMapper) {
        this.guestInformationRepository = guestInformationRepository;
        this.guestInformationMapper = guestInformationMapper;
    }

    public List<GuestInformationDTO> getAllGuestsInRoom(int roomId) {
        List<GuestInformation> guests = guestInformationRepository.getGuestsInRoomById(roomId);
        return guests
                .stream()
                .map(guestInformationMapper::guestInformationToDTO)
                .toList();
    }

    public List<GuestInformationDTO> getAllGuests() {
        List<GuestInformation> allGuests = guestInformationRepository.findAll();
        return allGuests
                .stream()
                .map(guestInformationMapper::guestInformationToDTO)
                .toList();
    }
}
