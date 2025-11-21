package com.example.practica3.services;

import com.example.practica3.response.TransferenciasResponse;
import java.util.List;

public interface TransferenciasServicesInterface {
    List<TransferenciasResponse> listarTransferenciasPorCuenta(Long idCuenta);
}
