package com.adopcion.backend.repositories;

import com.adopcion.backend.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findByAdopterId(Long userId);
    List<Adoption> findByPetId(Long petId);
}
