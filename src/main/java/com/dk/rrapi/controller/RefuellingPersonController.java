package com.dk.rrapi.controller;

import com.dk.rrapi.dto.request.RefuellingPersonReqDTO;
import com.dk.rrapi.dto.response.RefuellingPersonResDTO;
import com.dk.rrapi.persistence.entity.RefuellingPerson;
import com.dk.rrapi.service.RefuellingPersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/refuellingPerson")
public class RefuellingPersonController {
    private RefuellingPersonService refuellingPersonService;

    public RefuellingPersonController(RefuellingPersonService refuellingPersonService) {
        this.refuellingPersonService = refuellingPersonService;
    }

    @GetMapping()
    public ResponseEntity<List<RefuellingPersonResDTO>> getAllRefuellingPeople(){
        List<RefuellingPersonResDTO> refuellingPeople = this.refuellingPersonService.getAll();
        return new ResponseEntity<>(refuellingPeople, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RefuellingPersonResDTO> addRefuellingPerson(@Valid @RequestBody RefuellingPersonReqDTO refuellingPersonReqDTO){
        RefuellingPersonResDTO refuellingPersonResDTO = this.refuellingPersonService.addRefuelling( refuellingPersonReqDTO );
        return new ResponseEntity<>(refuellingPersonResDTO, HttpStatus.CREATED);
    }
}
