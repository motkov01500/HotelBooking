package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.FacilityDTO;
import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.dto.GuestReviewDTO;
import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.dto.reservation.CreateReservationDTO;
import com.spring.project.hotelbooking.dto.reservation.ReservationDTO;
import com.spring.project.hotelbooking.dto.room.RoomDTO;
import com.spring.project.hotelbooking.entity.Facility;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.entity.GuestReview;
import com.spring.project.hotelbooking.entity.Reservation;
import com.spring.project.hotelbooking.entity.Room;
import com.spring.project.hotelbooking.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-09T13:35:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationDTO reservationToReservationDTO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId( reservation.getId() );
        reservationDTO.setCheckInDate( reservation.getCheckInDate() );
        reservationDTO.setCheckOutDate( reservation.getCheckOutDate() );
        reservationDTO.setRooms( roomSetToRoomDTOSet( reservation.getRooms() ) );
        reservationDTO.setGuestInformations( guestInformationSetToGuestInformationDTOSet( reservation.getGuestInformations() ) );
        reservationDTO.setUser( userToUserDTO( reservation.getUser() ) );
        reservationDTO.setResConfirmation( reservation.isResConfirmation() );

        afterMapping( reservationDTO );

        return reservationDTO;
    }

    @Override
    public Reservation createReservationToReservation(CreateReservationDTO createReservationDTO) {
        if ( createReservationDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setCheckInDate( createReservationDTO.getCheckInDate() );
        reservation.setCheckOutDate( createReservationDTO.getCheckOutDate() );
        reservation.setNumAdults( createReservationDTO.getNumAdults() );
        reservation.setNumChildren( createReservationDTO.getNumChildren() );
        reservation.setResConfirmation( createReservationDTO.isResConfirmation() );

        return reservation;
    }

    protected GuestReviewDTO guestReviewToGuestReviewDTO(GuestReview guestReview) {
        if ( guestReview == null ) {
            return null;
        }

        GuestReviewDTO guestReviewDTO = new GuestReviewDTO();

        guestReviewDTO.setComment( guestReview.getComment() );

        return guestReviewDTO;
    }

    protected Set<GuestReviewDTO> guestReviewSetToGuestReviewDTOSet(Set<GuestReview> set) {
        if ( set == null ) {
            return null;
        }

        Set<GuestReviewDTO> set1 = new LinkedHashSet<GuestReviewDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( GuestReview guestReview : set ) {
            set1.add( guestReviewToGuestReviewDTO( guestReview ) );
        }

        return set1;
    }

    protected FacilityDTO facilityToFacilityDTO(Facility facility) {
        if ( facility == null ) {
            return null;
        }

        FacilityDTO facilityDTO = new FacilityDTO();

        facilityDTO.setName( facility.getName() );

        return facilityDTO;
    }

    protected Set<FacilityDTO> facilitySetToFacilityDTOSet(Set<Facility> set) {
        if ( set == null ) {
            return null;
        }

        Set<FacilityDTO> set1 = new LinkedHashSet<FacilityDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Facility facility : set ) {
            set1.add( facilityToFacilityDTO( facility ) );
        }

        return set1;
    }

    protected RoomDTO roomToRoomDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomNumber( room.getRoomNumber() );
        roomDTO.setGuestReviews( guestReviewSetToGuestReviewDTOSet( room.getGuestReviews() ) );
        roomDTO.setFacilities( facilitySetToFacilityDTOSet( room.getFacilities() ) );
        roomDTO.setDescription( room.getDescription() );
        roomDTO.setNumberOfPlaces( room.getNumberOfPlaces() );
        roomDTO.setPricePerNight( (int) room.getPricePerNight() );

        return roomDTO;
    }

    protected Set<RoomDTO> roomSetToRoomDTOSet(Set<Room> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoomDTO> set1 = new LinkedHashSet<RoomDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Room room : set ) {
            set1.add( roomToRoomDTO( room ) );
        }

        return set1;
    }

    protected GuestInformationDTO guestInformationToGuestInformationDTO(GuestInformation guestInformation) {
        if ( guestInformation == null ) {
            return null;
        }

        GuestInformationDTO guestInformationDTO = new GuestInformationDTO();

        guestInformationDTO.setFirstName( guestInformation.getFirstName() );
        guestInformationDTO.setLastName( guestInformation.getLastName() );

        return guestInformationDTO;
    }

    protected Set<GuestInformationDTO> guestInformationSetToGuestInformationDTOSet(Set<GuestInformation> set) {
        if ( set == null ) {
            return null;
        }

        Set<GuestInformationDTO> set1 = new LinkedHashSet<GuestInformationDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( GuestInformation guestInformation : set ) {
            set1.add( guestInformationToGuestInformationDTO( guestInformation ) );
        }

        return set1;
    }

    protected UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setEmail( user.getEmail() );
        userDTO.setRole( user.getRole() );

        return userDTO;
    }
}
