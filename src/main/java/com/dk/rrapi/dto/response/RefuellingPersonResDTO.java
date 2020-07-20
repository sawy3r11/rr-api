package com.dk.rrapi.dto.response;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @Builder
public class RefuellingPersonResDTO {
    private Long id;
    private String name;
    private String surname;
    private String postCode;
    private String city;
    private String address;
}
