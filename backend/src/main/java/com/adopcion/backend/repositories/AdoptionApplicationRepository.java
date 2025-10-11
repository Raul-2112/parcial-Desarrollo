package com.adopcion.backend.repositories;

import com.adopcion.backend.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
    List<AdoptionApplication> findByApplicantId(Long userId);
    List<AdoptionApplication> findByPetId(Long petId);
}