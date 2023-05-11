package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.room.RoomDTO;
import com.spring.project.hotelbooking.entity.Facility;
import com.spring.project.hotelbooking.entity.Room;
import com.spring.project.hotelbooking.exception.RoomNotFoundException;
import com.spring.project.hotelbooking.mapper.RoomMapper;
import com.spring.project.hotelbooking.repository.RoomRepository;
import com.spring.project.hotelbooking.xmlparser.FacilityTagXML;
import com.spring.project.hotelbooking.xmlparser.RoomTagXML;
import com.spring.project.hotelbooking.xmlparser.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final FacilityService facilityService;
    private final RoomMapper roomMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       FacilityService facilityService,
                       RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.facilityService = facilityService;
        this.roomMapper = roomMapper;
    }

    public Room getRoomById(int id) {
        return roomRepository
                .findById(id)
                .orElseThrow(()-> new RoomNotFoundException("That room not exist."));
    }

    public Set<Facility> getCollectionFacilitiesById(List<Integer> facilityIds) {
        Set<Facility> facilities = new HashSet<>();
        facilityIds
                .forEach(id-> facilities.add(facilityService.getFacilityById(id)));
        return facilities;
    }

    public List<RoomDTO> updateRooms(File xmlFile) throws JAXBException {
        XMLParser parser = new XMLParser();
        List<RoomTagXML> updatedRooms = parser.parseXMLRooms(xmlFile).getRoomList();
        for (RoomTagXML room: updatedRooms) {
            Room oldRoom = roomRepository
                    .findById(room.getId())
                    .orElseThrow(()-> new RoomNotFoundException("That room not exist."));
            if(room.getFacilities() != null) {
                List<Integer> roomIdsFromXML = getRoomFacilityIdsFromRoomTagXML(room);
                Set<Facility> facilities = getCollectionFacilitiesById(roomIdsFromXML);
                oldRoom.setFacilities(facilities);
            }
            oldRoom.setDescription(room.getDescription());
            oldRoom.setPricePerNight(room.getPricePerNight());
            oldRoom.setNumberOfPlaces(room.getNumberOfPlaces());
            oldRoom.setRoomNumber(room.getRoomNumber());
            roomRepository.save(oldRoom);
        }
        return roomRepository
                .findAll()
                .stream()
                .map(roomMapper::roomToDTO)
                .toList();
    }

    private List<Integer> getRoomFacilityIdsFromRoomTagXML(RoomTagXML room) {
        return room.getFacilities()
                .getFacilityTagElements()
                .stream()
                .map(FacilityTagXML::getId)
                .toList();
    }
}
