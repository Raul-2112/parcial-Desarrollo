package com.example.practica3.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosResponse {
    private Long idUsuario;
    private String nombre;
    private String apellido;
    private String documento;
}
