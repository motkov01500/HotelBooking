package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    @Query("FROM Room R WHERE R.roomNumber=:roomNumber")
    Room getRoomByRoomNumber(@Param("roomNumber")int roomNumber);
}
