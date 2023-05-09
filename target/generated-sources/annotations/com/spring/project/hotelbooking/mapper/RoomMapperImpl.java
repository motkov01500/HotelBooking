package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.FacilityDTO;
import com.spring.project.hotelbooking.dto.GuestReviewDTO;
import com.spring.project.hotelbooking.dto.room.RoomDTO;
import com.spring.project.hotelbooking.entity.Facility;
import com.spring.project.hotelbooking.entity.GuestReview;
import com.spring.project.hotelbooking.entity.Room;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-09T13:35:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTO roomToDTO(Room room) {
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
}
