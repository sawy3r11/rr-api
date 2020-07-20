package com.dk.rrapi.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class ApiException{

    public ApiException(Integer code, LocalDateTime timeStamp, String message) {
        this.code = code;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    private Integer code;
    private LocalDateTime timeStamp;
    private String message;
    private Map<String, String> errors;
}
