package com.dk.rrapi.repo;

import com.dk.rrapi.persistence.entity.Refuelling;
import com.dk.rrapi.persistence.repo.RefuellingRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class RefuellingRepoTest {
    @Autowired
    private RefuellingRepo refuellingRepo;

    @Test
    public void addRefuelling(){
        Refuelling refuelling = Refuelling
                .builder()
                .amount( new BigDecimal(20))
                .price( new BigDecimal(4.20) )
                .creationDateTime(LocalDateTime.now() )
                .refuelingDateTime( LocalDateTime.now() )
                .build();
        this.refuellingRepo.save( refuelling );
        assert( refuelling.getId()!=null );
    }

    @Test
    public void addRefuelling2(){
        Refuelling refuelling = Refuelling
                .builder()
                .price( new BigDecimal(4.20) )
                .creationDateTime(LocalDateTime.now() )
                .refuelingDateTime( LocalDateTime.now() )
                .build();
        this.refuellingRepo.save( refuelling );
        assert( refuelling.getId()!=null );
    }
}
