package com.dk.rrapi.persistence.entity;

import com.dk.rrapi.persistence.entity.types.FuelType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "REFUELLING")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Refuelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refuelling_id")
    private Long id;
    @NotNull
    @Column(name="creation_date_time")
    private LocalDateTime creationDateTime;
    @NotNull
    @Column(name="refueling_date_time")
    private LocalDateTime refuelingDateTime;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal amount;
    @NotNull
    @Column(name = "odometer_reading")
    private Double odometerReading;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="fuel_type")
    private FuelType fuelType;
    @ManyToOne()
    private RefuellingPerson refuellingPerson;
}
