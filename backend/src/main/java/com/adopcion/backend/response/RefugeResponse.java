package com.adopcion.backend.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RefugeResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String description;
    private LocalDateTime createdAt;
}