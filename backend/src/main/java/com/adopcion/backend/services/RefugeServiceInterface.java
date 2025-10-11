package com.adopcion.backend.services;

import com.adopcion.backend.request.RefugeRequest;
import com.adopcion.backend.response.RefugeResponse;

import java.util.List;

public interface RefugeServiceInterface {
    RefugeResponse create(RefugeRequest req);
    RefugeResponse update(Long id, RefugeRequest req);
    RefugeResponse findById(Long id);
    List<RefugeResponse> findAll();
    void delete(Long id);
}