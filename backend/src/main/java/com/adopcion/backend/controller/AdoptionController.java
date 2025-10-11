package com.adopcion.backend.controller;

import com.adopcion.backend.request.AdoptionRequest;
import com.adopcion.backend.response.AdoptionResponse;
import com.adopcion.backend.services.AdoptionServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
@CrossOrigin(origins = "*")
public class AdoptionController {

    private final AdoptionServiceInterface adoptionService;

    public AdoptionController(AdoptionServiceInterface adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping
    public ResponseEntity<List<AdoptionResponse>> getAll() {
        return ResponseEntity.ok(adoptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionResponse> getById(@PathVariable Long id) {
        AdoptionResponse resp = adoptionService.findById(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<AdoptionResponse> create(@RequestBody AdoptionRequest request) {
        AdoptionResponse created = adoptionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adoptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
