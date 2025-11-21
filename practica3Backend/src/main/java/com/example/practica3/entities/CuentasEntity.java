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
@Table(name = "Cuentas")

public class CuentasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Cuenta", nullable = false)
    private Long idCuenta;

    @Column(name = "Numero_Cuenta", nullable = false, length = 20)
    private String numeroCuenta;

    @Column(name = "Saldo", nullable = false)
    private double saldo;

    @OneToMany(mappedBy = "cuentas")
    private List<TransferenciasEntity> transferencias = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id_Usuario")
    private UsuariosEntity usuario;
}
