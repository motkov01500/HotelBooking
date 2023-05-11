package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.entity.Reservation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDTO reservationToReservationDTO(Reservation reservation);

    Reservation createReservationToReservation(CreateReservationDTO createReservationDTO);

    @AfterMapping
    default void afterMapping(@MappingTarget ReservationDTO reservationDTO) {
        reservationDTO.getGuestInformations().forEach(guest->guest.setFullName(guest.getFirstName() + " " + guest.getLastName()));
    }
}
