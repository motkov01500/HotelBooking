package com.spring.project.hotelbooking.exception.handler;

import com.spring.project.hotelbooking.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(HttpServletRequest req, UserNotFoundException exception) {
        String message = "User is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }
    @ExceptionHandler(FacilityNotFoundException.class)
    public ResponseEntity<Object> handleFacilityNotFoundException(HttpServletRequest req,FacilityNotFoundException exception) {
        String message = "Facility is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleReservationNotFoundException(HttpServletRequest req,ReservationNotFoundException exception) {
        String message = "Reservation is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }
    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<Object> handleUserRoleNotFoundException(HttpServletRequest req,UserRoleNotFoundException exception) {
        String message = "User role is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }
    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleRoomNotFoundException(HttpServletRequest req,RoomNotFoundException exception) {
        String message = "Room is not found!";
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND,message));
    }
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
