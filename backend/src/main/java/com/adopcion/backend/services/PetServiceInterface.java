package com.adopcion.backend.services;

import com.adopcion.backend.request.PetRequest;
import com.adopcion.backend.response.PetResponse;

import java.util.List;

public interface PetServiceInterface {
    PetResponse create(PetRequest req);
    PetResponse update(Long id, PetRequest req);
    PetResponse findById(Long id);
    List<PetResponse> findAll();
    void delete(Long id);
    List<PetResponse> findBySpecies(String species);
}
