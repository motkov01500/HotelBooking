package com.spring.project.hotelbooking.exception.handler;

import com.spring.project.hotelbooking.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        String message = "User is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    @ExceptionHandler(FacilityNotFoundException.class)
    public ResponseEntity<Object> handleFacilityNotFoundException(FacilityNotFoundException exception) {
        String message = "Facility is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(ReservationNotFoundException exception) {
        String message = "Reservation is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<Object> handleUserRoleNotFoundException(UserRoleNotFoundException exception) {
        String message = "User role is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException exception) {
        String message = "Room is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    @ExceptionHandler(LoyaltyTypeNotFoundException.class)
    public ResponseEntity<Object> handleLoyaltyTypeNotFoundException(LoyaltyTypeNotFoundException exception) {
        String message = "LoyaltyType is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
