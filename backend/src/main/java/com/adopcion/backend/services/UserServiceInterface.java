package com.adopcion.backend.services;

import com.adopcion.backend.request.UserRequest;
import com.adopcion.backend.response.UserResponse;

import java.util.List;

public interface UserServiceInterface {
    UserResponse create(UserRequest req);
    UserResponse update(Long id, UserRequest req);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
    void delete(Long id);
}
