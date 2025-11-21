package com.example.practica3.services;

import com.example.practica3.entities.CuentasEntity;
import com.example.practica3.entities.UsuariosEntity;
import com.example.practica3.mappers.CuentasMapper;
import com.example.practica3.mappers.UsuariosMapper;
import com.example.practica3.repositories.CuentasRepository;
import com.example.practica3.repositories.UsuariosRepository;
import com.example.practica3.request.UsuariosRequest;
import com.example.practica3.response.CuentasResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class UsuariosServicesImpl implements UsuariosServicesInterface {

    private final UsuariosRepository usuariosRepository;
    private final CuentasRepository cuentasRepository;
    private final UsuariosMapper usuariosMapper;
    private final CuentasMapper cuentasMapper;

    @Override
    public CuentasResponse registrarUsuario(UsuariosRequest request) {

        UsuariosEntity usuario = usuariosMapper.toEntity(request);
        UsuariosEntity usuarioGuardado = usuariosRepository.save(usuario);

        CuentasEntity cuenta = CuentasEntity.builder()
                .numeroCuenta(generarNumeroCuenta(6))
                .saldo(0.0)
                .usuario(usuarioGuardado)
                .build();

        CuentasEntity cuentaGuardada = cuentasRepository.save(cuenta);

        return cuentasMapper.toResponse(cuentaGuardada);
    }

    private String generarNumeroCuenta(int longitud) {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    @Override
    public boolean login(String correo, String contraseña) {
        return usuariosRepository.findByCorreoAndContraseña(correo, contraseña).isPresent();
    }


}
