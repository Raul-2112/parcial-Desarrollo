package com.adopcion.backend.services;

import com.adopcion.backend.request.AdoptionApplicationRequest;
import com.adopcion.backend.response.AdoptionApplicationResponse;

import java.util.List;

public interface AdoptionApplicationServiceInterface {
    AdoptionApplicationResponse create(AdoptionApplicationRequest req);
    AdoptionApplicationResponse updateStatus(Long id, String status);
    AdoptionApplicationResponse findById(Long id);
    List<AdoptionApplicationResponse> findAll();
    void delete(Long id);
}