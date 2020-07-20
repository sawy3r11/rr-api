package com.dk.rrapi.persistence.repo;

import com.dk.rrapi.persistence.entity.Refuelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefuellingRepo extends JpaRepository<Refuelling, Long> {
    Optional<Refuelling> getById(Long id);
}
