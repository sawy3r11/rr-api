package com.dk.rrapi.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ApiException{
    private Integer code;
    private LocalDateTime timeStamp;
    private String message;
    private Map<String, String> errors;
}
