package com.example.practica3.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String mensaje;
    private UsuariosResponse usuario;
    private CuentasResponse cuenta;
}