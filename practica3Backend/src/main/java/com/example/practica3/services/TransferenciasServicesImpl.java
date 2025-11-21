package com.example.practica3.services;

import com.example.practica3.entities.TransferenciasEntity;
import com.example.practica3.mappers.TransferenciasMapper;
import com.example.practica3.repositories.TransferenciasRepository;
import com.example.practica3.response.TransferenciasResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferenciasServicesImpl implements TransferenciasServicesInterface {

    private final TransferenciasRepository transferenciasRepository;
    private final TransferenciasMapper transferenciasMapper;

    @Override
    public List<TransferenciasResponse> listarTransferenciasPorCuenta(Long idCuenta) {
        List<TransferenciasEntity> transferencias = transferenciasRepository.findByCuentas_IdCuenta(idCuenta);
        return transferencias.stream()
                .map(transferenciasMapper::toResponse)
                .collect(Collectors.toList());
    }

}
