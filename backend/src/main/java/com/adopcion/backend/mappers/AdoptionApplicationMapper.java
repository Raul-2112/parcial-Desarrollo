package com.adopcion.backend.mappers;

import com.adopcion.backend.entity.AdoptionApplication;
import com.adopcion.backend.request.AdoptionApplicationRequest;
import com.adopcion.backend.response.AdoptionApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdoptionApplicationMapper {

    // No mapeamos applicant/pet aquí; service los setea.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    @Mapping(target = "pet", ignore = true)
    AdoptionApplication toEntity(AdoptionApplicationRequest r);

    // Mapea applicant.id y pet.id hacia response
    @Mapping(source = "applicant.id", target = "applicantId")
    @Mapping(source = "pet.id", target = "petId")
    AdoptionApplicationResponse toResponse(AdoptionApplication a);
}