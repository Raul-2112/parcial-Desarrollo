package com.adopcion.backend.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
    private String phone;
    private LocalDateTime createdAt;
}