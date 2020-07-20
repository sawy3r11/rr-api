package com.dk.rrapi.service;

import com.dk.rrapi.dto.request.RefuellingReqDTO;
import com.dk.rrapi.dto.response.RefuellingResDTO;
import com.dk.rrapi.exception.ResourceNotFoundException;
import com.dk.rrapi.persistence.entity.Refuelling;
import com.dk.rrapi.persistence.repo.RefuellingRepo;
import com.dk.rrapi.util.SourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional()
public class RefuellingService {
    private RefuellingRepo refuellingRepo;
    private SourceMapper sourceMapper;

    public RefuellingService(RefuellingRepo refuellingRepo, SourceMapper sourceMapper) {
        this.refuellingRepo = refuellingRepo;
        this.sourceMapper = sourceMapper;
    }

    public RefuellingResDTO getRefuelling(Long id){
        Optional<Refuelling> refuellingOptional = this.refuellingRepo.getById(id);
        if( refuellingOptional.isPresent()==false ){
            throw new ResourceNotFoundException(Refuelling.class.getSimpleName());
        }else {
            RefuellingResDTO refuellingResDTO = this.sourceMapper.map(refuellingOptional.get());
            return refuellingResDTO;
        }
    }

    public RefuellingResDTO addRefuelling(RefuellingReqDTO refuellingReqDTO){
        Refuelling refuelling = Refuelling
                .builder()
                .price(refuellingReqDTO.getPrice())
                .amount(refuellingReqDTO.getAmount())
                .refuelingDateTime(refuellingReqDTO.getRefuelingDateTime())
                .creationDateTime(LocalDateTime.now())
                .fuelType(refuellingReqDTO.getFuelType())
                .odometerReading(refuellingReqDTO.getOdometerReading())
                .build();
        this.refuellingRepo.save( refuelling );

        RefuellingResDTO refuellingResDTO = this.sourceMapper.map(refuelling);
        return refuellingResDTO;
    }

    public RefuellingResDTO updateRefuelling(Long id, RefuellingReqDTO refuellingReqDTO){
        Optional<Refuelling> refuellingOptional = this.refuellingRepo.getById(id);
        if(refuellingOptional.isPresent()==false){
            throw new ResourceNotFoundException(Refuelling.class.getSimpleName());
        }else{
            Refuelling refuelling = refuellingOptional.get();
            refuelling.setPrice( refuellingReqDTO.getPrice() );
            refuelling.setAmount( refuellingReqDTO.getAmount() );
            refuelling.setRefuelingDateTime( refuellingReqDTO.getRefuelingDateTime() );
            refuelling.setFuelType( refuellingReqDTO.getFuelType() );
            refuelling.setOdometerReading( refuellingReqDTO.getOdometerReading() );
            RefuellingResDTO refuellingResDTO = this.sourceMapper.map(refuelling);
            return refuellingResDTO;
        }
    }

    public void delete(Long id){
        Optional<Refuelling> refuellingOptional = this.refuellingRepo.getById( id );
        refuellingOptional.ifPresent(x->{
            this.refuellingRepo.delete( refuellingOptional.get() );
        });
    }

    public List<RefuellingResDTO> getAll(){
        List<Refuelling> refuellings = this.refuellingRepo.findAll();
        List<RefuellingResDTO> refuellingResDTOS = this.sourceMapper.map(refuellings);

        return refuellingResDTOS;
    }
}
