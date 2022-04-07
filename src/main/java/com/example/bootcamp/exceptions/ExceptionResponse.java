package com.example.bootcamp.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ExceptionResponse {

    private HttpStatus httpStatus;
   private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(HttpStatus httpStatus,Date timestamp, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp=timestamp;
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
