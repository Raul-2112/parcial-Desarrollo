package com.example.practica3.services;

import com.example.practica3.request.UsuariosRequest;
import com.example.practica3.response.CuentasResponse;

public interface UsuariosServicesInterface {
    CuentasResponse registrarUsuario(UsuariosRequest request);
    boolean login(String correo, String contraseña);
}
