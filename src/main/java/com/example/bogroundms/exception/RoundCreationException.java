package com.example.bogroundms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoundCreationException extends RuntimeException {

    public RoundCreationException() {
    }

    public RoundCreationException(String message) {
        super(message);
    }

    public RoundCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
