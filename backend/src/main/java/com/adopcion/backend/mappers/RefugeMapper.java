package com.adopcion.backend.mappers;

import com.adopcion.backend.entity.Refuge;
import com.adopcion.backend.request.RefugeRequest;
import com.adopcion.backend.response.RefugeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefugeMapper {
    Refuge toEntity(RefugeRequest r);
    RefugeResponse toResponse(Refuge e);
}