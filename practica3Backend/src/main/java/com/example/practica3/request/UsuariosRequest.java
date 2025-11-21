package com.example.practica3.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosRequest {
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;
    private String correo;
    private String contraseña;
}
