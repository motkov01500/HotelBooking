package com.spring.project.hotelbooking.xmlparser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "Rooms")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomsTagXML {

    @XmlElement(name = "Room")
    private List<RoomTagXML> roomList = new ArrayList<>();

    public void setRoomList(List<RoomTagXML> roomList) {
        this.roomList = roomList;
    }

    public List<RoomTagXML> getRoomList() {
        return Collections.unmodifiableList(roomList);
    }
}
