package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.CreateGuestInformationDTO;
import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestInformationMapper {

    @Mapping(target = "fullName", expression = "java(guestInformation.getFirstName() + guestInformation.getLastName())")
    GuestInformationDTO guestInformationToDTO(GuestInformation guestInformation);

    GuestInformation guestDTOToGuest(CreateGuestInformationDTO createGuestInformationDTO);
}
