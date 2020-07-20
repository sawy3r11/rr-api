package com.dk.rrapi.util;

import com.dk.rrapi.dto.response.RefuellingResDTO;
import com.dk.rrapi.persistence.entity.Refuelling;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceMapper {

    public RefuellingResDTO map(Refuelling refuelling){
        RefuellingResDTO refuellingResDTO = RefuellingResDTO
                .builder()
                .amount( refuelling.getAmount() )
                .creationDateTime(refuelling.getCreationDateTime())
                .price(refuelling.getPrice())
                .refuelingDateTime(refuelling.getRefuelingDateTime())
                .id(refuelling.getId())
                .fuelType(refuelling.getFuelType())
                .odometerReading(refuelling.getOdometerReading())
                .build();
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

}
