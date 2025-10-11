package com.adopcion.backend.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AdoptionRequest {
    private Long applicationId;
    private Long adopterId;
    private Long petId;
    private LocalDateTime adoptedAt;
    private String notes;
}