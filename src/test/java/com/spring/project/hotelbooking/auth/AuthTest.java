package com.spring.project.hotelbooking.auth;

import com.spring.project.hotelbooking.entity.*;
import com.spring.project.hotelbooking.util.DeleteDataFromDatabaseUtils;
import com.spring.project.hotelbooking.util.PersistDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AuthTest {

    private final MockMvc mockMvc;
    private final DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils;
    private final PersistDataUtils persistDataUtils;

    @Autowired
    public AuthTest(MockMvc mockMvc, DeleteDataFromDatabaseUtils deleteDataFromDatabaseUtils, PersistDataUtils persistDataUtils) {
        this.mockMvc = mockMvc;
        this.deleteDataFromDatabaseUtils = deleteDataFromDatabaseUtils;
        this.persistDataUtils = persistDataUtils;
    }

    @AfterEach
    public void cleanDatabase() {
        deleteDataFromDatabaseUtils.cleanDatabase();
    }

    @Test
    @WithMockUser(username="test", password = "test", roles = {"administrator"})
    public void testAdminRequest() throws Exception {
        //given
        Set<GuestInformation> firstGuestInformation = persistDataUtils.persistGuestInformations();

        //when
        ResultActions response = mockMvc.perform(get("/guestinformation/allGuests"));

        //then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$",hasSize(2)));
        response.andDo(print());
    }

    @Test
    @WithMockUser(username="test", password = "test", roles = {"guest"})
    public void testGuestRequest() throws Exception {
        //given
        Loyalty loyalty = persistDataUtils.persistLoyalty("Golden",10);
        User user = persistDataUtils.persistUser("test@gmail.com", loyalty);

        //when
        ResultActions response = mockMvc.perform(get("/user/getUsers"));

        //then
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    @WithMockUser(username="test", password = "test", roles = {"guest"})
    public void testRequestWithoutNeededRole() throws Exception {
        //given
        List<Reservation> reservation = persistDataUtils.persistReservationOnCurrentDate();

        //when
        ResultActions response = mockMvc.perform(get("/reservations/getAllNotCheckedInReservations"));

        //then
        response.andExpect(status().isForbidden());
    }

    @Test
    public void testIsAuthenticated() throws Exception {
        //given
        Set<GuestInformation> firstGuestInformation = persistDataUtils.persistGuestInformations();

        //when
        ResultActions response = mockMvc.perform(get("/guestinformation/allGuests"));

        //then
        response.andExpect(status().isUnauthorized());
    }
}
