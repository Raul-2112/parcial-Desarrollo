package com.example.practica3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Transferencias")

public class TransferenciasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Transferencia", nullable = false)
    private Long idTransferencia;

    @Column (name= "Fecha", nullable = false)
    private LocalDate fecha;

    @Column (name= "Total", nullable                                                                                               = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "Id_Cuenta", nullable = false)
    private CuentasEntity cuentas;
}
