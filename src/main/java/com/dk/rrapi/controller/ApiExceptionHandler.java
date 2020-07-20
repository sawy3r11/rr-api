package com.dk.rrapi.controller;

import com.dk.rrapi.dto.response.ApiException;
import com.dk.rrapi.exception.ArgumentNotValidException;
import com.dk.rrapi.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> resourceDontFound(ResourceNotFoundException resourceNotFoundException){
        ApiException apiException = new ApiException(404, LocalDateTime.now(), resourceNotFoundException.getMessage());
        logger.error( apiException.toString() );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                                     HttpHeaders headers,
                                                                                     HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        x-> x.getField(),
                        x->x.getDefaultMessage()
                ));
        body.put("errors", errors);

        ApiException argumentNotValidException = ApiException.builder()
                .code(status.value())
                .message("NOT VALID DATA PROVIDED!")
                .timeStamp(LocalDateTime.now())
                .errors(errors)
                .build();
        logger.error( argumentNotValidException.toString() );
        return new ResponseEntity<>(argumentNotValidException, status);

    }
}
