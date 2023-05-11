package com.spring.project.hotelbooking.exception;

public class FacilityNotFoundException extends RuntimeException{

    public FacilityNotFoundException(String message) {
        super(message);
    }
}
