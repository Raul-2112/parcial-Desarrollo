package com.example.practica3.controlles;

import com.example.practica3.entities.CuentasEntity;
import com.example.practica3.entities.UsuariosEntity;
import com.example.practica3.mappers.CuentasMapper;
import com.example.practica3.mappers.UsuariosMapper;
import com.example.practica3.repositories.CuentasRepository;
import com.example.practica3.repositories.UsuariosRepository;
import com.example.practica3.request.LoginRequest;
import com.example.practica3.request.UsuariosRequest;
import com.example.practica3.response.CuentasResponse;
import com.example.practica3.response.LoginResponse;
import com.example.practica3.response.UsuariosResponse;
import com.example.practica3.services.UsuariosServicesImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private final UsuariosServicesImpl usuarios;
    private final UsuariosRepository usuariosRepository;
    private final CuentasRepository cuentasRepository;
    private final UsuariosMapper usuariosMapper;
    private final CuentasMapper cuentasMapper;

    @PostMapping("/registro")
    public ResponseEntity<CuentasResponse> registrarUsuario(@RequestBody UsuariosRequest request) {
        CuentasResponse response = usuarios.registrarUsuario(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<UsuariosEntity> usuarioOpt = usuariosRepository.findByCorreo(request.getCorreo());

        if (usuarioOpt.isPresent() && usuarioOpt.get().getContraseña().equals(request.getClave())) {

            UsuariosEntity usuario = usuarioOpt.get();
            Optional<CuentasEntity> cuentaOpt = cuentasRepository.findByUsuario_IdUsuario(usuario.getIdUsuario());

            UsuariosResponse usuarioResponse = usuariosMapper.toResponse(usuario);

            CuentasResponse cuentaResponse = null;
            if (cuentaOpt.isPresent()) {
                cuentaResponse = cuentasMapper.toResponse(cuentaOpt.get());
            }

            LoginResponse response = new LoginResponse("Inicio de sesión exitoso", usuarioResponse, cuentaResponse);
            return ResponseEntity.ok(response);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Credenciales incorrectas", null, null));
        }
    }
}