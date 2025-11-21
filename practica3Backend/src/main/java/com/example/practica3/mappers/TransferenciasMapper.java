package com.example.practica3.mappers;

import com.example.practica3.entities.TransferenciasEntity;
import com.example.practica3.response.TransferenciasResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransferenciasMapper {

    @Mapping(target = "idCuenta", source = "cuentas.idCuenta")
    TransferenciasResponse toResponse(TransferenciasEntity entity);

    List<TransferenciasResponse> toResponseList(List<TransferenciasEntity> entities);
}

