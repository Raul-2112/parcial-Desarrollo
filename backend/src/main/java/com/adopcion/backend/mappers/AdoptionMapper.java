package com.adopcion.backend.mappers;

import com.adopcion.backend.entity.Adoption;
import com.adopcion.backend.request.AdoptionRequest;
import com.adopcion.backend.response.AdoptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AdoptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "adopter", ignore = true)
    @Mapping(target = "pet", ignore = true)
    Adoption toEntity(AdoptionRequest r);

    @Mapping(source = "application.id", target = "applicationId")
    @Mapping(source = "adopter.id", target = "adopterId")
    @Mapping(source = "pet.id", target = "petId")
    AdoptionResponse toResponse(Adoption a);
}