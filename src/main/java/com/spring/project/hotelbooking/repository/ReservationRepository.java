package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("FROM Reservation RES LEFT JOIN RES.user US WHERE US.email = :email")
    List<Reservation> getReservationByUserEmail(@Param("email") String email);

    @Query("FROM Reservation RES WHERE RES.resConfirmation = false")
    List<Reservation> getAllNotCheckedInReservations();

    @Modifying
    @Query("DELETE FROM Reservation RES WHERE RES.bookingId = :bookingId")
    void deleteByBookingId(@Param("bookingId") String bookingId);
}