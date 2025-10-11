package com.adopcion.backend.controller;

import com.adopcion.backend.request.RefugeRequest;
import com.adopcion.backend.response.RefugeResponse;
import com.adopcion.backend.services.RefugeServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refuges")
@CrossOrigin(origins = "*")
public class RefugeController {

    private final RefugeServiceInterface refugeService;

    public RefugeController(RefugeServiceInterface refugeService) {
        this.refugeService = refugeService;
    }

    @GetMapping
    public ResponseEntity<List<RefugeResponse>> getAll() {
        return ResponseEntity.ok(refugeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefugeResponse> getById(@PathVariable Long id) {
        RefugeResponse resp = refugeService.findById(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<RefugeResponse> create(@RequestBody RefugeRequest request) {
        RefugeResponse created = refugeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefugeResponse> update(@PathVariable Long id, @RequestBody RefugeRequest request) {
        RefugeResponse updated = refugeService.update(id, request);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        refugeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
