package com.adopcion.backend.controller;

import com.adopcion.backend.request.AdoptionApplicationRequest;
import com.adopcion.backend.response.AdoptionApplicationResponse;
import com.adopcion.backend.services.AdoptionApplicationServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class AdoptionApplicationController {

    private final AdoptionApplicationServiceInterface applicationService;

    public AdoptionApplicationController(AdoptionApplicationServiceInterface applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<AdoptionApplicationResponse>> getAll() {
        return ResponseEntity.ok(applicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionApplicationResponse> getById(@PathVariable Long id) {
        AdoptionApplicationResponse resp = applicationService.findById(id);
        if (resp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<AdoptionApplicationResponse> create(@RequestBody AdoptionApplicationRequest request) {
        AdoptionApplicationResponse created = applicationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AdoptionApplicationResponse> updateStatus(@PathVariable Long id,
                                                                    @RequestParam("status") String status) {
        AdoptionApplicationResponse updated = applicationService.updateStatus(id, status);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
