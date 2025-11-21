package com.example.practica3.repositories;

import com.example.practica3.entities.CuentasEntity;
import com.example.practica3.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentasRepository extends JpaRepository<CuentasEntity, Long> {

    Optional<CuentasEntity> findByUsuario_IdUsuario(Long idUsuario);

}
