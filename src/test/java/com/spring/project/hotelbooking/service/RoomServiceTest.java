package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.entity.Room;
import com.spring.project.hotelbooking.repository.RoomRepository;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private RoomService roomService;

    @Test
    public void testGetRoomById() {
        //given
        Room room = TestDataUtils.createRoom();

        //when
        when(roomRepository.findById(room.getId())).thenReturn(Optional.of(room));
        Room expectedRoom = roomService.getRoomById(room.getId());

        //then

        Assertions.assertEquals(expectedRoom,room);
    }
}