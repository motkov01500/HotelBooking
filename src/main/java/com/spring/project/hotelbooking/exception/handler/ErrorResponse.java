package com.spring.project.hotelbooking.exception.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ErrorResponse {
    private HttpStatus httpStatus;
    private LocalDate currentDate;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.currentDate = LocalDate.now();
        this.message = message;
    }

    //region getters
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public String getMessage() {
        return message;
    }
    //endregion

    //region setters
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //endregion
}
