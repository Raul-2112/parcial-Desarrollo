package com.adopcion.backend.services;

import com.adopcion.backend.entity.AdoptionApplication;
import com.adopcion.backend.entity.Pet;
import com.adopcion.backend.entity.User;
import com.adopcion.backend.mappers.AdoptionApplicationMapper;
import com.adopcion.backend.repositories.AdoptionApplicationRepository;
import com.adopcion.backend.repositories.PetRepository;
import com.adopcion.backend.repositories.UserRepository;
import com.adopcion.backend.request.AdoptionApplicationRequest;
import com.adopcion.backend.response.AdoptionApplicationResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionApplicationServiceImpl implements AdoptionApplicationServiceInterface {

    private final AdoptionApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AdoptionApplicationMapper mapper;

    public AdoptionApplicationServiceImpl(AdoptionApplicationRepository applicationRepository,
                                          UserRepository userRepository,
                                          PetRepository petRepository,
                                          AdoptionApplicationMapper mapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    @Override
    public AdoptionApplicationResponse create(AdoptionApplicationRequest req) {
        User applicant = userRepository.findById(req.getApplicantId()).orElseThrow(() -> new RuntimeException("User not found"));
        Pet pet = petRepository.findById(req.getPetId()).orElseThrow(() -> new RuntimeException("Pet not found"));

        AdoptionApplication entity = mapper.toEntity(req);
        entity.setApplicant(applicant);
        entity.setPet(pet);
        entity.setStatus(entity.getStatus() == null ? "PENDIENTE" : entity.getStatus());

        AdoptionApplication saved = applicationRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public AdoptionApplicationResponse updateStatus(Long id, String status) {
        AdoptionApplication existing = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        existing.setStatus(status);
        AdoptionApplication saved = applicationRepository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    public AdoptionApplicationResponse findById(Long id) {
        return applicationRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<AdoptionApplicationResponse> findAll() {
        return applicationRepository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}