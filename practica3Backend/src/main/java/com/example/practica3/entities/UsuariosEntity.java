package com.example.practica3.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Usuarios")
public class UsuariosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Apellido", nullable = false, length = 20)
    private String apellido;

    @Column(name = "Documento", nullable = false, length = 10, unique = true)
    private String documento;

    @Column(name = "Telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "Correo", nullable = false, length = 30)
    private String correo;

    @Column(name = "Contraseña", nullable = false, length = 250)
    private String contraseña;


}
