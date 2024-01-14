package com.spring.project.hotelbooking.controller;

import com.spring.project.hotelbooking.entity.GuestInformation;
import com.spring.project.hotelbooking.util.DeleteDataFromDatabaseUtils;
import com.spring.project.hotelbooking.util.PersistDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class GuestInformationControllerTest {

    private final MockMvc mockMvc;
    private final DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils;
    private final PersistDataUtils persistDataUtils;

    @Autowired
    public GuestInformationControllerTest(MockMvc mockMvc, DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils, PersistDataUtils persistDataUtils) {
        this.mockMvc = mockMvc;
        this.deleteDataFromDatabaseUtils = deleteDataFromDatabaseUtils;
        this.persistDataUtils = persistDataUtils;
    }

    @AfterEach
    public void cleanDatabase() {
        deleteDataFromDatabaseUtils.cleanDatabase();
    }

    @Test
    public void testGetGuests() throws Exception {
        //given
        Set<GuestInformation> firstGuestInformation = persistDataUtils.persistGuestInformations();

        //when
        ResultActions response = mockMvc.perform(get("/guestinformation/allGuests"));

        //then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$",hasSize(2)));
        response.andDo(print());
        response.andReturn();
    }
}