package com.dk.rrapi.dto.response;

import com.dk.rrapi.persistence.entity.types.FuelType;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RefuellingResDTO {
    private Long id;
    private LocalDateTime creationDateTime;
    private LocalDateTime refuelingDateTime;
    private BigDecimal price;
    private BigDecimal amount;
    private Double odometerReading;
    private FuelType fuelType;
}
