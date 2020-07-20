package com.dk.rrapi.controller;

import com.dk.rrapi.dto.request.RefuellingReqDTO;
import com.dk.rrapi.dto.response.RefuellingResDTO;
import com.dk.rrapi.service.RefuellingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/refuelling")
public class RefuellingController {
    private RefuellingService refuellingService;

    public RefuellingController(RefuellingService refuellingService) {
        this.refuellingService = refuellingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuellingResDTO> getRefuelling(@PathVariable Long id){
        RefuellingResDTO refuellingResDTO = this.refuellingService.getRefuelling( id );
        return new ResponseEntity<>(refuellingResDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RefuellingResDTO> addRefuelling(@Valid @RequestBody RefuellingReqDTO refuellingReqDTO){
        RefuellingResDTO refuellingResDTO = this.refuellingService.addRefuelling( refuellingReqDTO );
        return new ResponseEntity<>(refuellingResDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefuellingResDTO> updateRefuelling(@PathVariable Long id, @Valid @RequestBody RefuellingReqDTO refuellingReqDTO){
        RefuellingResDTO refuellingResDTO = this.refuellingService.updateRefuelling(id, refuellingReqDTO);
        return new ResponseEntity<>(refuellingResDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRefuelling(@PathVariable Long id){
        this.refuellingService.delete( id );
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<RefuellingResDTO>> getAll(){
        List<RefuellingResDTO> refuellingResDTOS = this.refuellingService.getAll();
        return new ResponseEntity<>(refuellingResDTOS, HttpStatus.OK);
    }
}
