package com.dk.rrapi.exception;

import com.dk.rrapi.dto.response.ApiException;

import java.time.LocalDateTime;
import java.util.Map;

public class ArgumentNotValidException extends RuntimeException {
    private Map<String, String> errors;

    public ArgumentNotValidException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
