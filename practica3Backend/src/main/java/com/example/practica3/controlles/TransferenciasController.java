package com.example.practica3.controlles;

import com.example.practica3.response.TransferenciasResponse;
import com.example.practica3.services.TransferenciasServicesImpl;
import com.example.practica3.services.TransferenciasServicesInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/transferencias")
@RequiredArgsConstructor
public class TransferenciasController {

    private final TransferenciasServicesImpl transferenciasService;

    @GetMapping("/usuario/{idCuenta}")
    public ResponseEntity<List<TransferenciasResponse>> listarTransferenciasPorUsuario(@PathVariable Long idCuenta) {
        List<TransferenciasResponse> response = transferenciasService.listarTransferenciasPorCuenta(idCuenta);
        return ResponseEntity.ok(response);
    }

}
