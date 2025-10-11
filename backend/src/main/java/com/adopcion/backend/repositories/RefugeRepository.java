package com.adopcion.backend.repositories;

import com.adopcion.backend.entity.Refuge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefugeRepository extends JpaRepository<Refuge, Long> {
}