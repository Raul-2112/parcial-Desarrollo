package com.adopcion.backend.services;

import com.adopcion.backend.request.AdoptionRequest;
import com.adopcion.backend.response.AdoptionResponse;

import java.util.List;

public interface AdoptionServiceInterface {
    AdoptionResponse create(AdoptionRequest req);
    AdoptionResponse findById(Long id);
    List<AdoptionResponse> findAll();
    void delete(Long id);
}