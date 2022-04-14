package com.example.bootcamp.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ExceptionResponse {

    private HttpStatus httpStatus;
   private Date timestamp;
    private String message;

    public ExceptionResponse(HttpStatus httpStatus,Date timestamp, String message) {
        this.httpStatus = httpStatus;
        this.timestamp=timestamp;
        this.message = message;
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
}
