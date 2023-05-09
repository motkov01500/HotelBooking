package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.CreateGuestInformationDTO;
import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-09T13:35:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class GuestInformationMapperImpl implements GuestInformationMapper {

    @Override
    public GuestInformationDTO guestInformationToDTO(GuestInformation guestInformation) {
        if ( guestInformation == null ) {
            return null;
        }

        GuestInformationDTO guestInformationDTO = new GuestInformationDTO();

        guestInformationDTO.setFirstName( guestInformation.getFirstName() );
        guestInformationDTO.setLastName( guestInformation.getLastName() );

        guestInformationDTO.setFullName( guestInformation.getFirstName() + guestInformation.getLastName() );

        return guestInformationDTO;
    }

    @Override
    public GuestInformation guestDTOToGuest(CreateGuestInformationDTO createGuestInformationDTO) {
        if ( createGuestInformationDTO == null ) {
            return null;
        }

        GuestInformation guestInformation = new GuestInformation();

        guestInformation.setPhoneNumber( createGuestInformationDTO.getPhoneNumber() );
        guestInformation.setFirstName( createGuestInformationDTO.getFirstName() );
        guestInformation.setLastName( createGuestInformationDTO.getLastName() );
        guestInformation.setPin( createGuestInformationDTO.getPin() );
        if ( createGuestInformationDTO.getAge() != null ) {
            guestInformation.setAge( Integer.parseInt( createGuestInformationDTO.getAge() ) );
        }

        return guestInformation;
    }
}
