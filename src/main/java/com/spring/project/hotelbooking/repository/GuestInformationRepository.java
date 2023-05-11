package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.GuestInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestInformationRepository extends JpaRepository<GuestInformation,Integer> {

    @Query("FROM GuestInformation G JOIN G.reservations R JOIN R.rooms r WHERE r.id = :roomId AND (current_date() > R.checkInDate AND current_date() <= R.checkOutDate)")
    List<GuestInformation> getGuestsInRoomById(@Param("roomId") int roomId);
}
