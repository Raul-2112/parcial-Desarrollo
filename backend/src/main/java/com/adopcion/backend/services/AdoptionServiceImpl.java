package com.adopcion.backend.services;

import com.adopcion.backend.entity.Adoption;
import com.adopcion.backend.entity.AdoptionApplication;
import com.adopcion.backend.entity.Pet;
import com.adopcion.backend.entity.User;
import com.adopcion.backend.mappers.AdoptionMapper;
import com.adopcion.backend.repositories.AdoptionApplicationRepository;
import com.adopcion.backend.repositories.AdoptionRepository;
import com.adopcion.backend.repositories.PetRepository;
import com.adopcion.backend.repositories.UserRepository;
import com.adopcion.backend.request.AdoptionRequest;
import com.adopcion.backend.response.AdoptionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionServiceImpl implements AdoptionServiceInterface {

    private final AdoptionRepository adoptionRepository;
    private final AdoptionApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AdoptionMapper mapper;

    public AdoptionServiceImpl(AdoptionRepository adoptionRepository,
                               AdoptionApplicationRepository applicationRepository,
                               UserRepository userRepository,
                               PetRepository petRepository,
                               AdoptionMapper mapper) {
        this.adoptionRepository = adoptionRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AdoptionResponse create(AdoptionRequest req) {
        AdoptionApplication app = applicationRepository.findById(req.getApplicationId()).orElseThrow(() -> new RuntimeException("Application not found"));
        User adopter = userRepository.findById(req.getAdopterId()).orElseThrow(() -> new RuntimeException("User not found"));
        Pet pet = petRepository.findById(req.getPetId()).orElseThrow(() -> new RuntimeException("Pet not found"));

        Adoption entity = mapper.toEntity(req);
        entity.setApplication(app);
        entity.setAdopter(adopter);
        entity.setPet(pet);
        entity.setAdoptedAt(req.getAdoptedAt() != null ? req.getAdoptedAt() : LocalDateTime.now());

        // mark pet unavailable and save within transaction
        pet.setAvailable(false);
        petRepository.save(pet);

        // update application status
        app.setStatus("APROBADA");
        applicationRepository.save(app);

        Adoption saved = adoptionRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public AdoptionResponse findById(Long id) {
        return adoptionRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<AdoptionResponse> findAll() {
        return adoptionRepository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        adoptionRepository.deleteById(id);
    }
}