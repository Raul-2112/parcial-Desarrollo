package com.example.practica3.request;
import com.example.practica3.response.UsuariosResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentasRequest {
    private UsuariosResponse usuario;
}
