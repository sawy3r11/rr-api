package com.dk.rrapi.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class RefuellingPersonReqDTO {
    @NotNull
    @Length(min = 1, max = 50, message = "name length <2, 50>")
    private String name;
    @NotNull
    @Length(min = 1, max = 50, message = "surname length <2, 50>")
    private String surname;
    @Length(max = 20, message = "postCode length <0, 20>")
    private String postCode;
    @Length(max = 100, message = "city length <0, 100>")
    private String city;
    @Length(max = 250, message = "address length <0, 50>")
    private String address;
}
