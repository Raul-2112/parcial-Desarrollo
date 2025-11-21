package com.example.practica3.repositories;

import com.example.practica3.entities.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {

    Optional<UsuariosEntity> findByCorreoAndContraseña(String correo, String contraseña);
    Optional<UsuariosEntity> findByCorreo(String correo);

}

