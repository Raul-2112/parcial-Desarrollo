package com.adopcion.backend.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdoptionApplicationResponse {
    private Long id;
    private Long applicantId;
    private Long petId;
    private String message;
    private String status;
    private LocalDateTime createdAt;
}
