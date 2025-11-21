package com.example.practica3.mappers;

import com.example.practica3.entities.CuentasEntity;
import com.example.practica3.entities.UsuariosEntity;
import com.example.practica3.request.CuentasRequest;
import com.example.practica3.request.UsuariosRequest;
import com.example.practica3.response.CuentasResponse;
import com.example.practica3.response.UsuariosResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentasMapper {

    @Mapping(target = "usuario", source = "usuario")
    CuentasEntity toEntity(CuentasRequest request);

    @Mapping(target = "usuario", source = "usuario")
    CuentasResponse toResponse(CuentasEntity entity);

    UsuariosEntity toEntity(UsuariosRequest request);
    UsuariosResponse toResponse(UsuariosEntity entity);
}
