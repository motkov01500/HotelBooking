package com.spring.project.hotelbooking.dto.reservation;

import java.time.LocalDate;

public class ReservationExtendDateDto {

    private LocalDate checkInDate;

    //region getter
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    //endregion

    //region setter
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    //endregion
}
