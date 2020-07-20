package com.dk.rrapi.persistence.repo;

import com.dk.rrapi.persistence.entity.Refuelling;
import com.dk.rrapi.persistence.entity.RefuellingPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefuellingPersonRepo extends JpaRepository<RefuellingPerson, Long> {
    Optional<RefuellingPerson> getById(Long id);
}
