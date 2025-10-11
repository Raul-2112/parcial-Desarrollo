package com.adopcion.backend.services;

import com.adopcion.backend.entity.User;
import com.adopcion.backend.mappers.UserMapper;
import com.adopcion.backend.repositories.UserRepository;
import com.adopcion.backend.request.UserRequest;
import com.adopcion.backend.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse create(UserRequest req) {
        User u = userMapper.toEntity(req);
        User saved = userRepository.save(u);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Long id, UserRequest req) {
        User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        // map simple fields manually (avoid overwriting id/createdAt)
        existing.setUsername(req.getUsername());
        existing.setEmail(req.getEmail());
        existing.setPhone(req.getPhone());
        existing.setRole(req.getRole());
        if (req.getPassword() != null) existing.setPassword(req.getPassword());
        User saved = userRepository.save(existing);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse findById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponse).orElse(null);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}