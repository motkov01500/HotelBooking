package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.GuestInformationDTO;
import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.mapper.GuestInformationMapper;
import com.spring.project.hotelbooking.repository.GuestInformationRepository;
import com.spring.project.hotelbooking.util.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestInformationServiceTest {

    @Mock
    GuestInformationRepository guestInformationRepository;
    @Mock
    GuestInformationMapper guestInformationMapper;
    @InjectMocks
    GuestInformationService guestInformationService;

    @Test
    public void getAllGuests() {
        //given
        List<GuestInformation> guestInformations = new ArrayList<>();
        GuestInformation guestInformation = TestDataUtils.createGuestInformation("0544258623","0926518486");
        GuestInformation secondGuestInformation = TestDataUtils.createGuestInformation("0843166251","0963954915");
        guestInformations.add(guestInformation);
        guestInformations.add(secondGuestInformation);

        //when
        when(guestInformationRepository.findAll())
                .thenReturn(guestInformations);
        List<GuestInformationDTO> expectedGuests = guestInformationService.getAllGuests();

        //then
        Assertions.assertEquals(expectedGuests.size(),guestInformations.size());
    }
}