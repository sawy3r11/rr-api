package com.dk.rrapi.service;

import com.dk.rrapi.dto.request.RefuellingPersonReqDTO;
import com.dk.rrapi.dto.request.RefuellingReqDTO;
import com.dk.rrapi.dto.response.RefuellingPersonResDTO;
import com.dk.rrapi.dto.response.RefuellingResDTO;
import com.dk.rrapi.exception.ResourceNotFoundException;
import com.dk.rrapi.persistence.entity.Refuelling;
import com.dk.rrapi.persistence.entity.RefuellingPerson;
import com.dk.rrapi.persistence.repo.RefuellingPersonRepo;
import com.dk.rrapi.persistence.repo.RefuellingRepo;
import com.dk.rrapi.util.SourceMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RefuellingPersonService {
    private RefuellingPersonRepo refuellingPersonRepo;
    private SourceMapper sourceMapper;

    public RefuellingPersonService(RefuellingPersonRepo refuellingPersonRepo, SourceMapper sourceMapper) {
        this.refuellingPersonRepo = refuellingPersonRepo;
        this.sourceMapper = sourceMapper;
    }

    public RefuellingPersonResDTO getRefuellingPerson(Long id){
        Optional<RefuellingPerson> refuellingPersonOptional = this.refuellingPersonRepo.getById(id);
        if( refuellingPersonOptional.isPresent()==false ){
            throw new ResourceNotFoundException(RefuellingPerson.class.getSimpleName());
        }else {
            RefuellingPersonResDTO refuellingPersonResDTO = this.sourceMapper.map(refuellingPersonOptional.get());
            return refuellingPersonResDTO;
        }
    }

    public RefuellingPersonResDTO addRefuelling(RefuellingPersonReqDTO refuellingPersonReqDTO){
        RefuellingPerson refuellingPerson = RefuellingPerson
                .builder()
                .name(refuellingPersonReqDTO.getName())
                .surname(refuellingPersonReqDTO.getSurname())
                .city(refuellingPersonReqDTO.getCity())
                .address(refuellingPersonReqDTO.getAddress())
                .postCode(refuellingPersonReqDTO.getPostCode())
                .build();
        this.refuellingPersonRepo.save( refuellingPerson );

        RefuellingPersonResDTO refuellingPersonResDTO = this.sourceMapper.map(refuellingPerson);
        return refuellingPersonResDTO;
    }

    public RefuellingPersonResDTO updateRefuelling(Long id, RefuellingPersonReqDTO refuellingPersonReqDTO){
        Optional<RefuellingPerson> refuellingPersonOptional = this.refuellingPersonRepo.getById(id);
        if(refuellingPersonOptional.isPresent()==false){
            throw new ResourceNotFoundException(Refuelling.class.getSimpleName());
        }else{
            RefuellingPerson refuellingPerson = refuellingPersonOptional.get();
            refuellingPerson.setName( refuellingPersonReqDTO.getName() );
            refuellingPerson.setSurname( refuellingPersonReqDTO.getSurname() );
            refuellingPerson.setCity(refuellingPersonReqDTO.getCity());
            refuellingPerson.setAddress(refuellingPersonReqDTO.getAddress());
            refuellingPerson.setPostCode(refuellingPersonReqDTO.getPostCode());
            RefuellingPersonResDTO refuellingPersonResDTO = this.sourceMapper.map(refuellingPerson);
            return refuellingPersonResDTO;
        }
    }

    public void delete(Long id){
        Optional<RefuellingPerson> refuellingPersonOptional = this.refuellingPersonRepo.getById( id );
        refuellingPersonOptional.ifPresent(x->{
            this.refuellingPersonRepo.delete( refuellingPersonOptional.get() );
        });
    }

    public List<RefuellingPersonResDTO> getAll(){
        List<RefuellingPerson> refuellingPeople = this.refuellingPersonRepo.findAll();
        List<RefuellingPersonResDTO> refuellingResDTOS = this.sourceMapper.mapRefuellingPeople( refuellingPeople );

        return refuellingResDTOS;
    }
}
