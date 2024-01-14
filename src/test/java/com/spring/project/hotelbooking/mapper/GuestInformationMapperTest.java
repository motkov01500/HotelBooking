package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.CreateGuestInformationDTO;
import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestInformationMapperTest {

    GuestInformationMapper guestInformationMapper;

    @BeforeEach
    public void setUp() {
        guestInformationMapper = new GuestInformationMapperImpl();
    }

    @Test
    public void guestInformationToDTO() {
        //given
        GuestInformation guestInformation = TestDataUtils.createGuestInformation("0544258623","0926518486");
        String guestFullName = guestInformation.getFirstName() + guestInformation.getLastName();

        //when
        GuestInformationDTO guestInformationDTO = guestInformationMapper.guestInformationToDTO(guestInformation);

        //then
        Assertions.assertEquals(guestFullName,guestInformationDTO.getFullName());

    }

    @Test
    public void guestDTOToGuest() {
        //given
        CreateGuestInformationDTO guestInformationDTO = TestDataUtils.createCreateGuestInformationDTO();

        //when
        GuestInformation guestInformation = guestInformationMapper.guestDTOToGuest(guestInformationDTO);

        //then
        Assertions.assertEquals(guestInformation.getFirstName(), guestInformationDTO.getFirstName());
        Assertions.assertEquals(guestInformation.getLastName(), guestInformationDTO.getLastName());
        Assertions.assertEquals(guestInformation.getPin(), guestInformationDTO.getPin());
    }
}