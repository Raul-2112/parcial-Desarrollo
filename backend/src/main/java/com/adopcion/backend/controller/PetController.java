package com.adopcion.backend.controller;

import com.adopcion.backend.request.PetRequest;
import com.adopcion.backend.response.PetResponse;
import com.adopcion.backend.services.PetServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
public class PetController {

    private final PetServiceInterface petService;

    public PetController(PetServiceInterface petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> getAll() {
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> getById(@PathVariable Long id) {
        PetResponse resp = petService.findById(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PetResponse>> findBySpecies(@RequestParam(name = "species", required = false) String species) {
        if (species == null || species.isBlank()) {
            return ResponseEntity.ok(petService.findAll());
        }
        return ResponseEntity.ok(petService.findBySpecies(species));
    }

    @PostMapping
    public ResponseEntity<PetResponse> create(@RequestBody PetRequest request) {
        PetResponse created = petService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> update(@PathVariable Long id, @RequestBody PetRequest request) {
        PetResponse updated = petService.update(id, request);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
