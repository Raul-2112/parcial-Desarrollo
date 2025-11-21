package com.example.practica3.repositories;

import com.example.practica3.entities.TransferenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciasRepository extends JpaRepository<TransferenciasEntity, Long> {
    List<TransferenciasEntity> findByCuentas_IdCuenta(Long idCuenta);
}
