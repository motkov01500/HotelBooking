package com.spring.project.hotelbooking.xmlparser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

class ParseRoomXMLTest {

    private XMLParser xmlParser;

    @BeforeEach
    public void setUp() {
        xmlParser = new XMLParser();
    }

    @Test
    public void testRoomParsing() throws JAXBException {
        //given
        File file = new File("src/test/resources/example.xml");

        //when
        RoomsTagXML roomsTagXML = xmlParser.parseXMLRooms(file);
        List<RoomTagXML> roomsList = roomsTagXML.getRoomList();
        RoomTagXML firstRoom = roomsList.get(0);

        //then
        Assertions.assertEquals(101,firstRoom.getRoomNumber());
        Assertions.assertEquals(5,firstRoom.getNumberOfPlaces());
        Assertions.assertEquals(225,firstRoom.getPricePerNight());
        Assertions.assertEquals(4,firstRoom.getFacilities().getFacilityTagElements().size());
    }

    @Test
    public void testCountOfParsedRooms() throws JAXBException {
        //given
        File file = new File("src/test/resources/example.xml");

        //when
        RoomsTagXML roomsTagXML = xmlParser.parseXMLRooms(file);
        List<RoomTagXML> roomsList = roomsTagXML.getRoomList();

        //then
        Assertions.assertEquals(2,roomsList.size());
    }
}