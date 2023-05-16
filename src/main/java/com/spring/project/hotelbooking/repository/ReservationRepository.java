package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("FROM Reservation RES JOIN RES.user U WHERE U.email = :email")
    List<Reservation> getReservationByUserEmail(@Param("email") String email);

    @Query("FROM Reservation RES WHERE RES.resConfirmation = false")
    List<Reservation> getAllNotCheckedInReservations();

    @Query("DELETE FROM Reservation RES WHERE RES.bookingId = :bookingId")
    void deleteByBookingId(@Param("bookingId") String bookingId);

    @Query("From Reservation RES JOIN RES.user U WHERE U.id = :userId AND (RES.createdAt BETWEEN (current_date() - 30) AND current_date())")
    List<Reservation> getUserReservationsForMonth(@Param("userId") int userId);
}