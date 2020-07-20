package com.dk.rrapi.dto.request;

import com.dk.rrapi.persistence.entity.types.FuelType;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RefuellingReqDTO {
    @NotNull(message = "refuelingDateTime must be provided")
    private LocalDateTime refuelingDateTime;
    @NotNull(message = "price must be provided")
    @DecimalMin(value = "0", inclusive = false, message = "price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "amount must be provided")
    @DecimalMin(value = "0", inclusive = false, message = "amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "odometerReading must be provided")
    @DecimalMin(value = "0", inclusive = false, message = "odometerReading must be greater than 0")
    private Double odometerReading;
    @NotNull(message = "fuelType must be provided")
    private FuelType fuelType;
    private Long refuellingPersonId;
}
