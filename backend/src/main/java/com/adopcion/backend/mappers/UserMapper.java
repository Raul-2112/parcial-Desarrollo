package com.adopcion.backend.mappers;


import com.adopcion.backend.entity.User;
import com.adopcion.backend.request.UserRequest;
import com.adopcion.backend.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequest r);
    UserResponse toResponse(User u);
}