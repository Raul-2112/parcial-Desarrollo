package com.example.practica3.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentasResponse {
    private Long idCuenta;
    private String numeroCuenta;
    private double saldo;
    private UsuariosResponse usuario;
}
