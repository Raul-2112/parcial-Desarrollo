package com.example.practica3.request;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferenciasRequest {
    private LocalDate fecha;
    private double total;
    private Long idCuenta;
}
