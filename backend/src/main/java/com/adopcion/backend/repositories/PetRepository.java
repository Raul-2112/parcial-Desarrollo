package com.adopcion.backend.repositories;

import com.adopcion.backend.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findBySpeciesIgnoreCase(String species);
    List<Pet> findByAvailable(Boolean available);
    List<Pet> findByRefugeId(Long refugeId);
}