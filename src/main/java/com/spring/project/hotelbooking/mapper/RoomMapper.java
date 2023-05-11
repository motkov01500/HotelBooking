package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.room.RoomDTO;
import com.spring.project.hotelbooking.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomDTO roomToDTO(Room room);
}
