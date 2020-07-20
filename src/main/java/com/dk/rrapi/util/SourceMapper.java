package com.dk.rrapi.util;

import com.dk.rrapi.dto.response.RefuellingPersonResDTO;
import com.dk.rrapi.dto.response.RefuellingResDTO;
import com.dk.rrapi.persistence.entity.Refuelling;
import com.dk.rrapi.persistence.entity.RefuellingPerson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceMapper {

    public RefuellingResDTO map(Refuelling refuelling){
        RefuellingResDTO refuellingResDTO = null;
        if(refuelling!=null){
            refuellingResDTO = RefuellingResDTO
                    .builder()
                    .amount( refuelling.getAmount() )
                    .creationDateTime(refuelling.getCreationDateTime())
                    .price(refuelling.getPrice())
                    .refuelingDateTime(refuelling.getRefuelingDateTime())
                    .id(refuelling.getId())
                    .fuelType(refuelling.getFuelType())
                    .odometerReading(refuelling.getOdometerReading())
                    .refuellingPerson( this.map(refuelling.getRefuellingPerson() ))
                    .cost( refuelling.getPrice().multiply(refuelling.getAmount()) )
                    .build();
        }
        return refuellingResDTO;
    }

    public List<RefuellingResDTO> map(List<Refuelling> refuellings){
        List<RefuellingResDTO> refuellingResDTOS = new ArrayList<>();
        if(refuellings!=null){
            refuellingResDTOS = refuellings
                    .stream()
                    .map(x->this.map(x))
                    .collect(Collectors.toList());
        }

        return refuellingResDTOS;
    }

    public RefuellingPersonResDTO map(RefuellingPerson refuellingPerson){
        RefuellingPersonResDTO refuellingPersonResDTO = null;
        if(refuellingPerson!=null){
            refuellingPersonResDTO = RefuellingPersonResDTO
                    .builder()
                    .id(refuellingPerson.getId())
                    .address(refuellingPerson.getAddress())
                    .city(refuellingPerson.getCity())
                    .name(refuellingPerson.getName())
                    .surname(refuellingPerson.getSurname())
                    .postCode(refuellingPerson.getPostCode())
                    .build();
        }
        return refuellingPersonResDTO;
    }

    public List<RefuellingPersonResDTO> mapRefuellingPeople(List<RefuellingPerson> refuellingPeople){
        List<RefuellingPersonResDTO> refuellingPersonResDTOS = new ArrayList<>();
        if(refuellingPeople!=null){
            refuellingPersonResDTOS = refuellingPeople
                    .stream()
                    .map(x->this.map(x))
                    .collect(Collectors.toList());
        }
        return refuellingPersonResDTOS;
    }

}
