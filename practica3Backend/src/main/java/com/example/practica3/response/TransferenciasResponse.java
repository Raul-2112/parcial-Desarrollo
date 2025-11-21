package com.example.practica3.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciasResponse {
    private Long idTransferencia;
    private LocalDate fecha;
    private double total;
    private Long idCuenta;
}
