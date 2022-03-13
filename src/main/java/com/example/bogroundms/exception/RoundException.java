package com.example.bogroundms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoundException extends RuntimeException {

    public RoundException() {
    }

    public RoundException(String message) {
        super(message);
    }

    public RoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
