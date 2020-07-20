package com.dk.rrapi.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name="REFUELLING_PERSON")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RefuellingPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_person_id")
    private Long id;
    @NotNull
    @Column(length = 50)
    private String name;
    @NotNull
    @Column(length = 50)
    private String surname;
    @Column(length = 20)
    private String postCode;
    @Column(length = 100)
    private String city;
    @Column(length = 250)
    private String address;

    @OneToMany(
            mappedBy = "refuellingPerson",
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    private List<Refuelling> refuellings;
}
