package com.adopcion.backend.mappers;

import com.adopcion.backend.entity.Pet;
import com.adopcion.backend.request.PetRequest;
import com.adopcion.backend.response.PetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {

    // ignoramos id y refuge en la conversión desde request a entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "refuge", ignore = true)
    Pet toEntity(PetRequest r);

    // Mapear refugio -> refugeId en la respuesta
    @Mapping(source = "refuge.id", target = "refugeId")
    PetResponse toResponse(Pet p);
}