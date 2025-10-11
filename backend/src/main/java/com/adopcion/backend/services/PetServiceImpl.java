package com.adopcion.backend.services;

import com.adopcion.backend.entity.Pet;
import com.adopcion.backend.entity.Refuge;
import com.adopcion.backend.mappers.PetMapper;
import com.adopcion.backend.repositories.PetRepository;
import com.adopcion.backend.repositories.RefugeRepository;
import com.adopcion.backend.request.PetRequest;
import com.adopcion.backend.response.PetResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetServiceInterface {

    private final PetRepository petRepository;
    private final RefugeRepository refugeRepository;
    private final PetMapper petMapper;

    public PetServiceImpl(PetRepository petRepository,
                          RefugeRepository refugeRepository,
                          PetMapper petMapper) {
        this.petRepository = petRepository;
        this.refugeRepository = refugeRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponse create(PetRequest req) {
        Pet p = petMapper.toEntity(req);
        // load refuge and set
        Refuge r = refugeRepository.findById(req.getRefugeId()).orElseThrow(() -> new RuntimeException("Refuge not found"));
        p.setRefuge(r);
        Pet saved = petRepository.save(p);
        return petMapper.toResponse(saved);
    }

    @Override
    public PetResponse update(Long id, PetRequest req) {
        Pet existing = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
        existing.setName(req.getName());
        existing.setSpecies(req.getSpecies());
        existing.setBreed(req.getBreed());
        existing.setAge(req.getAge());
        existing.setGender(req.getGender());
        existing.setImageUrl(req.getImageUrl());
        existing.setDescription(req.getDescription());
        existing.setAvailable(req.getAvailable() != null ? req.getAvailable() : existing.getAvailable());
        if (req.getRefugeId() != null) {
            Refuge r = refugeRepository.findById(req.getRefugeId()).orElseThrow(() -> new RuntimeException("Refuge not found"));
            existing.setRefuge(r);
        }
        Pet saved = petRepository.save(existing);
        return petMapper.toResponse(saved);
    }

    @Override
    public PetResponse findById(Long id) {
        return petRepository.findById(id).map(petMapper::toResponse).orElse(null);
    }

    @Override
    public List<PetResponse> findAll() {
        return petRepository.findAll().stream().map(petMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public List<PetResponse> findBySpecies(String species) {
        return petRepository.findBySpeciesIgnoreCase(species).stream().map(petMapper::toResponse).collect(Collectors.toList());
    }
}