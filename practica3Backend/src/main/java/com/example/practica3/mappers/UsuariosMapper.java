package com.example.practica3.mappers;

import com.example.practica3.entities.UsuariosEntity;
import com.example.practica3.request.UsuariosRequest;
import com.example.practica3.response.UsuariosResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuariosMapper {
    UsuariosEntity toEntity(UsuariosRequest request);
    UsuariosResponse toResponse(UsuariosEntity entity);
}
