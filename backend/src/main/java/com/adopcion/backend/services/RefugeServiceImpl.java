package com.adopcion.backend.services;

import com.adopcion.backend.entity.Refuge;
import com.adopcion.backend.mappers.RefugeMapper;
import com.adopcion.backend.repositories.RefugeRepository;
import com.adopcion.backend.request.RefugeRequest;
import com.adopcion.backend.response.RefugeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefugeServiceImpl implements RefugeServiceInterface {

    private final RefugeRepository refugeRepository;
    private final RefugeMapper refugeMapper;

    public RefugeServiceImpl(RefugeRepository refugeRepository,
                             RefugeMapper refugeMapper) {
        this.refugeRepository = refugeRepository;
        this.refugeMapper = refugeMapper;
    }

    @Override
    public RefugeResponse create(RefugeRequest req) {
        Refuge r = refugeMapper.toEntity(req);
        Refuge saved = refugeRepository.save(r);
        return refugeMapper.toResponse(saved);
    }

    @Override
    public RefugeResponse update(Long id, RefugeRequest req) {
        Refuge existing = refugeRepository.findById(id).orElseThrow(() -> new RuntimeException("Refuge not found"));
        existing.setName(req.getName());
        existing.setAddress(req.getAddress());
        existing.setPhone(req.getPhone());
        existing.setEmail(req.getEmail());
        existing.setDescription(req.getDescription());
        Refuge saved = refugeRepository.save(existing);
        return refugeMapper.toResponse(saved);
    }

    @Override
    public RefugeResponse findById(Long id) {
        return refugeRepository.findById(id).map(refugeMapper::toResponse).orElse(null);
    }

    @Override
    public List<RefugeResponse> findAll() {
        return refugeRepository.findAll().stream().map(refugeMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        refugeRepository.deleteById(id);
    }
}