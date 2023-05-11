package com.spring.project.hotelbooking.dto.reservation;

import com.spring.project.hotelbooking.entity.GuestInformation;

import java.util.Set;

public class ReservationCheckInDTO {

    private Set<GuestInformation> guestInformation;
    private boolean resConfirmation;

    //region getters
    public Set<GuestInformation> getGuestInformation() {
        return guestInformation;
    }

    public boolean isResConfirmation() {
        return resConfirmation;
    }
    //endregion

    //region setters
    public void setGuestInformation(Set<GuestInformation> guestInformation) {
        this.guestInformation = guestInformation;
    }

    public void setResConfirmation(boolean resConfirmation) {
        this.resConfirmation = resConfirmation;
    }
    //endregion
}
